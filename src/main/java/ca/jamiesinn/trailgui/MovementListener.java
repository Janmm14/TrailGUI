package ca.jamiesinn.trailgui;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener
    implements Listener
{
    private final Main main;

    public MovementListener(Main main)
    {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (main.getConfig().getBoolean("disableWhenSpinning") &&
            event.getFrom().toVector()
                .equals(event.getTo().toVector())) //vectors only contain x,y, and z and not pitch and yaw
        {
            return;
        }
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

        final String worldName = player.getWorld().getName();
        for (String world : main.getConfig().getStringList("disabledWorlds"))
        {
            if (worldName.equalsIgnoreCase(world))
            {
                return;
            }
        }

        for (Trail trail : Trail.getTrailsOf(uuid))
        {
            trail.drawTrailAt(player);
        }
    }
}

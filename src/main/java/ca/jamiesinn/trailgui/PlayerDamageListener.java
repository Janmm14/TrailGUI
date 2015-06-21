package ca.jamiesinn.trailgui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener
    implements Listener
{

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player &&
            event.getEntity() instanceof Player)
        {
            Player hit = (Player) event.getEntity();

            Trail.removeTrails(hit.getUniqueId());
        }
    }
}

package ca.jamiesinn.trailgui;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener
    implements Listener
{
    private final Main main;

    public InventoryListener(Main main)
    {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event)
    {
        //TODO maybe allow player inventory changes even if trail inventory is open, requires more checks (is more complicated as the event does not have an appropiate method)
        if (!(event.getWhoClicked() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (!main.getInventoryHelper().hasInventoryOpen(player))
        {
            return;
        }

        event.setCancelled(true);
        updateInvDelayed(player);

        if (event.getClick() != ClickType.LEFT && event.getClick() != ClickType.RIGHT)
        {
            return;
        }
        Trail trail = Trail.getTrail(event.getSlot());
        if (trail == null)
        {
            //TODO check for remove button
            return;
        }
        final UUID uuid = player.getUniqueId();
        if (trail.hasTrail(uuid))
        {
            trail.removeTrail(uuid);
            //TODO send message
        } else
        {
            trail.addTrail(uuid);
            //TODO send message
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrag(InventoryDragEvent event)
    {
        if (!(event.getWhoClicked() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (!main.getInventoryHelper().hasInventoryOpen(player))
        {
            return;
        }

        event.setCancelled(true);
        updateInvDelayed(player);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event)
    {
        if (!(event.getPlayer() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getPlayer();

        final InventoryHelper invHelper = main.getInventoryHelper();
        if (!invHelper.hasInventoryOpen(player))
        {
            return;
        }
        invHelper.trackCloseInventory(player);
    }

    private void updateInvDelayed(final Player player)
    {
        main.getServer().getScheduler().runTask(main, new Runnable()
        {
            @Override
            public void run()
            {
                player.updateInventory();
            }
        });
    }
}

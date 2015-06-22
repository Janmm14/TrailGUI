package ca.jamiesinn.trailgui;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class InventoryHelper
{
    private static final String INVENTORY_MARKER_STR = "ci.jamiesinn.trailgui.InventoryMarker";
    private final Main main;
    private final ItemStack fillEmptyItem;
    private final FixedMetadataValue fixedMetadataValue;

    public InventoryHelper(Main main)
    {
        this.main = main;

        fixedMetadataValue = new FixedMetadataValue(main, null);

        // set up empty item
        fillEmptyItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        final ItemMeta im = fillEmptyItem.getItemMeta();
        im.setDisplayName(ChatColor.BLACK.toString());
        im.setLore(null);
        fillEmptyItem.setItemMeta(im);
    }

    public boolean hasInventoryOpen(Player player)
    {
        return player.hasMetadata(INVENTORY_MARKER_STR);
    }

    /**
     * <b>IMPORTANT TO CALL!</b>
     *
     * @param player the player who's inventory should be closed
     */
    public void trackCloseInventory(final Player player)
    {
        player.removeMetadata(INVENTORY_MARKER_STR, main);
    }

    public void openInventory(Player player)
    {
        final Collection<Trail> trails = Trail.getTrails();
        int maxSlot = 0;
        for (Trail trail : trails)
        {
            if (trail.getSlot() > maxSlot)
            {
                maxSlot = trail.getSlot();
            }
        }
        final Inventory inv = Bukkit.createInventory(null, (int) (Math.ceil(maxSlot / 9) * 9),
            ChatColor.GOLD + main.getConfig().getString("gui.name"));
        for (Trail trail : trails)
        {
            if (player.hasPermission(trail.getPermission()))
            {
                inv.setItem(trail.getSlot(), trail.getItem()); //TODO show in lore when player has trail
            } else
            {
                inv.setItem(trail.getSlot(), toNoPermItem(trail.getItem()));
            }
        }

        //fill empty places
        final ItemStack[] invContents = inv.getContents(); //TODO add item for removing all trails
        final int length = invContents.length;
        for (int i = 0; i < length; i++)
        {
            final ItemStack is = invContents[i];
            if (is == null || is.getType() == Material.AIR)
            {
                invContents[i] = fillEmptyItem;
            }
        }
        inv.setContents(invContents);
        player.setMetadata(INVENTORY_MARKER_STR, fixedMetadataValue);
        player.openInventory(inv);
    }

    private ItemStack toNoPermItem(ItemStack input)
    {
        final ItemMeta inputIm = input.getItemMeta();
        ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.RED.getData());

        final ItemMeta im = input.getItemMeta();
        if (inputIm.hasDisplayName())
        {
            im.setDisplayName(inputIm.getDisplayName());
        }
        if (inputIm.hasLore())
        {
            final List<String> lore = inputIm.getLore();
            final String noPermloreLine = main.getConfig().getString("gui.noPermLoreLine");
            if (noPermloreLine != null && !noPermloreLine.isEmpty())
            {
                if (main.getConfig().getBoolean("gui.noPermLoreInfoAtBottom"))
                {
                    lore.add(ChatColor.translateAlternateColorCodes('&', noPermloreLine));
                } else
                {
                    lore.add(0, ChatColor.translateAlternateColorCodes('&', noPermloreLine));
                }
            }
            im.setLore(lore);
        }
        is.setItemMeta(im);
        return is;
    }
}

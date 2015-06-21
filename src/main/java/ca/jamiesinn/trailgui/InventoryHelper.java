package ca.jamiesinn.trailgui;

import java.util.ArrayList;
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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class InventoryHelper
{
    private final Main main;
    private final ItemStack fillEmptyItem;

    public InventoryHelper(Main main)
    {
        this.main = main;
        fillEmptyItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        final ItemMeta im = fillEmptyItem.getItemMeta();
        im.setDisplayName(ChatColor.BLACK.toString());
        im.setLore(null);
        fillEmptyItem.setItemMeta(im);
    }

    public void openInventory(Player player, final int page)
    {
        final Collection<Trail> trails = Collections2.filter(Trail.getTrails(), new Predicate<Trail>()
        {
            @Override
            public boolean apply(Trail input)
            {
                return input.getPage() == page;
            }
        });
        int maxSlot = 0;
        for (Trail trail : trails)
        {
            if (trail.getSlot() > maxSlot)
            {
                maxSlot = trail.getSlot();
            }
        }
        final Inventory inv = Bukkit.createInventory(null, (int) (Math.ceil(maxSlot / 9) * 9 + 9),
            ChatColor.GOLD + "TrailGUI " + page + "/2"); //TODO make name configurable
        for (Trail trail : trails)
        {
            if (player.hasPermission(trail.getPermission()))
            {
                inv.setItem(trail.getSlot(), trail.getItem());
            } else
            {
                inv.setItem(trail.getSlot(), toNoPermItem(trail.getItem()));
            }
        }
        final int sizem9 = inv.getSize() - 9;
        int previous = sizem9 + getOption("previous", "slot");
        int next = sizem9 + getOption("next", "slot");

        //fill empty places
        final ItemStack[] invContents = inv.getContents();
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
    }

    private int getOption(String base, String option)
    {
        return main.getConfig().getInt(base + ".options." + option);
    }

    private ItemStack loadItem(String name)
    {
        final String matName = main.getConfig().getString(name + ".item.type");
        Material material = Material.matchMaterial(matName);
        if (material == null || material == Material.AIR)
        {
            main.getLogger()
                .warning("Incorrect item type in configuration of " + name + ": '" + matName + "' is no valid material name!");
            main.getLogger().warning("Because of that, " + name + " will be a stone in the gui!");
            material = Material.STONE;
        }
        final int data = main.getConfig().getInt(name + ".item.data");
        ItemStack is = new ItemStack(material, 1, (short) data);
        final ItemMeta im = is.getItemMeta();
        final String dispName = main.getConfig().getString(name + ".item.name");
        if (dispName != null && !dispName.isEmpty())
        {
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', dispName));
        }
        final List<String> lore = main.getConfig().getStringList(name + ".item.lore");
        //convert lore color code
        if (lore != null && !lore.isEmpty())
        {
            final String firstLoreLine = lore.get(0);
            if (firstLoreLine != null && !firstLoreLine.isEmpty())
            {
                List<String> newLore = new ArrayList<>(lore.size());
                for (String line : lore)
                {
                    if (!line.isEmpty())
                    {
                        newLore.add(ChatColor.translateAlternateColorCodes('&', line));
                    }
                }
                im.setLore(newLore);
            }
        }
        is.setItemMeta(im);
        return is;
    }

    private ItemStack toNoPermItem(ItemStack input)
    {
        final ItemMeta im = input.getItemMeta();
        input.setType(Material.STAINED_GLASS_PANE);
        input.setAmount(1);
        input.setDurability((short) DyeColor.RED.getData());
        final ItemMeta newIm = input.getItemMeta();
        if (im.hasDisplayName())
        {
            newIm.setDisplayName(im.getDisplayName());
        }
        if (im.hasLore())
        {
            newIm.setLore(im.getLore());
        }
        input.setItemMeta(newIm);
        return input;
    }
}

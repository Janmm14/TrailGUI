package ca.jamiesinn.trailgui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ca.jamiesinn.trailgui.util.Consumer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;

public final class TrailLoader
{

    private final Main main;

    private final Cache<UUID, Long> cooldownAngryVillager;

    public TrailLoader(Main main)
    {
        this.main = main;
        cooldownAngryVillager = CacheBuilder.newBuilder()
            .concurrencyLevel(4)
            .expireAfterWrite(getTrailOption("AngryVillager", "cooldown") * 50, TimeUnit.MILLISECONDS) //https://www.google.de/search?q=1+second+%2F+20
            .initialCapacity(50)
            .maximumSize(Long.MAX_VALUE)
            .build();
        TRAILS = ImmutableMap.<String, Consumer<Player>>builder()
            .put("AngryVillager", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Cloud", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Criticals", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("DripLava", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("DripWater", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Enchantment", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Spark", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Flame", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("HappyVillager", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("InstantSpell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("LargeSmoke", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Lava", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("MagicCrit", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("MobSpell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("MobSpellAmbient", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Note", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Portal", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("RedDust", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("ColoredRedDust", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Slime", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("SnowShovel", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("SnowballPoof", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Spell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Splash", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("TownAura", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Wake", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("WitchMagic", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("Hearts", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("EnderSignal", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .put("IconCrack", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation();
                }
            })
            .build();
    }

    private ImmutableMap<String, Consumer<Player>> TRAILS;

    public void loadTrails()
    {
        for (Map.Entry<String, Consumer<Player>> entry : TRAILS.entrySet())
        {
            ItemStack is = loadItem(entry.getKey());
            new Trail(entry.getKey(), "trailsgui.trail." + entry.getKey(), is, entry.getValue()); //constructor adds object to map in Trail class
        }
    }

    private ItemStack loadItem(String trailName)
    {
        final Material material = Material.matchMaterial(main.getConfig().getString(trailName + ".itemtype"));
        final int data = main.getConfig().getInt(trailName + ".item.data");
        ItemStack is = new ItemStack(material, 1, (short) data);
        final ItemMeta im = is.getItemMeta();
        final String dispName = main.getConfig().getString(trailName + ".item.name");
        if (dispName != null && !dispName.isEmpty())
        {
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', dispName));
        }
        final List<String> lore = main.getConfig().getStringList(trailName + ".item.lore");
        //convert lore color code
        if (lore != null && !lore.isEmpty())
        {
            List<String> newLore = new ArrayList<>(lore.size());
            for (String line : lore)
            {
                newLore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            im.setLore(newLore);
        }
        is.setItemMeta(im);
        return is;
    }

    private int getTrailOption(String trail, String option)
    {
        return main.getConfig().getInt(trail + ".options." + option);
    }
}

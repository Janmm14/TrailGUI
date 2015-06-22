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

import ca.jamiesinn.trailgui.libraries.ParticleEffect;
import ca.jamiesinn.trailgui.util.Consumer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;

public final class TrailLoader
{

    private final Main main;

    private ImmutableMap<String, Consumer<Player>> TRAILS;

    private final Cache<UUID, Long> cooldownAngryVillager, cooldownEnderSignal, cooldownHearts, cooldownNote;

    private static CacheBuilder<Object, Object> builderWithDefaultOpts()
    {
        return CacheBuilder.newBuilder()
            .concurrencyLevel(2)
            .initialCapacity(50)
            .maximumSize(Long.MAX_VALUE);
    }

    public TrailLoader(final Main main)
    {
        this.main = main;

        cooldownAngryVillager = builderWithDefaultOpts()
            .expireAfterWrite(getTrailOption("AngryVillager", "cooldown") * 50, TimeUnit.MILLISECONDS) //https://www.google.de/search?q=1+second+%2F+20
            .build();
        cooldownEnderSignal = builderWithDefaultOpts()
            .expireAfterWrite(getTrailOption("EnderSignal", "cooldown") * 50, TimeUnit.MILLISECONDS)
            .build();
        cooldownHearts = builderWithDefaultOpts()
            .expireAfterWrite(getTrailOption("Hearts", "cooldown") * 50, TimeUnit.MILLISECONDS)
            .build();
        cooldownNote = builderWithDefaultOpts()
            .expireAfterWrite(getTrailOption("Note", "cooldown") * 50, TimeUnit.MILLISECONDS)
            .build();
        TRAILS = ImmutableMap.<String, Consumer<Player>>builder()
            .put("AngryVillager", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation()
                        .add(0.0D, getTrailOption("AngryVillager", "displayLocation"), 0.0D); //TODO put cooldown related into method
                    final UUID uuid = player.getUniqueId();
                    final Long lastUsed = cooldownAngryVillager.getIfPresent(uuid);
                    final long timeMillis = System.currentTimeMillis();
                    if (lastUsed == null || lastUsed < (timeMillis - getTrailOption("AngryVillager", "cooldown") * 50))
                    {
                        cooldownAngryVillager.put(uuid, timeMillis);
                        ParticleEffect.VILLAGER_ANGRY
                            .display(0, 0, 0, 0, getTrailOption("AngryVillager", "amount"), loc, getTrailOption("AngryVillager", "range"));
                    }
                }
            })
            .put("Cloud", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Cloud", "displayLocation"), 0);
                    ParticleEffect.CLOUD
                        .display(0, 0, 0, 0, getTrailOption("Cloud", "amount"), loc, getTrailOption("Cloud", "range"));
                }
            })
            .put("Criticals", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Criticals", "displayLocation"), 0);
                    ParticleEffect.CRIT
                        .display(0, 0, 0, getTrailOption("Criticals", "speed"), getTrailOption("Criticals", "amount"), loc, getTrailOption("Criticals", "range"));

                }
            })
            .put("DripLava", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("DripLava", "displayLocation"), 0);
                    ParticleEffect.DRIP_LAVA
                        .display(0, 0, 0, getTrailOption("DripLava", "speed"), getTrailOption("DripLava", "amount"), loc, getTrailOption("DripLava", "range"));

                }
            })
            .put("DripWater", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("DripWater", "displayLocation"), 0);
                    ParticleEffect.DRIP_WATER
                        .display(0, 0, 0, getTrailOption("DripWater", "speed"), getTrailOption("DripWater", "amount"), loc, getTrailOption("DripWater", "range"));
                }
            })
            .put("Enchantment", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Enchantment", "displayLocation"), 0);
                    ParticleEffect.ENCHANTMENT_TABLE
                        .display(0, 0, 0, getTrailOption("Enchantment", "Enchantment-speed"), getTrailOption("Enchantment", "amount"), loc, getTrailOption("Enchantment", "range"));
                }
            })
            .put("Spark", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Spark", "displayLocation"), 0);
                    ParticleEffect.FIREWORKS_SPARK
                        .display(0, 0, 0, getTrailOption("Spark", "speed"), getTrailOption("Spark", "amount"), loc, getTrailOption("Spark", "range"));
                }
            })
            .put("Flame", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Flame", "displayLocation"), 0);
                    ParticleEffect.FLAME
                        .display(0, 0, 0, getTrailOption("Flame", "speed"), getTrailOption("Flame", "amount"), loc, getTrailOption("Flame", "range"));
                }
            })
            .put("HappyVillager", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("HappyVillager", "displayLocation"), 0);
                }
            })
            .put("InstantSpell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("InstantSpell", "displayLocation"), 0);
                }
            })
            .put("LargeSmoke", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("LargeSmoke", "displayLocation"), 0);
                }
            })
            .put("Lava", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Lava", "displayLocation"), 0);
                }
            })
            .put("MagicCrit", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("MagicCrit", "displayLocation"), 0);
                }
            })
            .put("MobSpell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("MobSpell", "displayLocation"), 0);
                }
            })
            .put("MobSpellAmbient", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("MobSpellAmbient", "displayLocation"), 0);
                }
            })
            .put("Note", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                { //cooldown
                    Location loc = player.getLocation().add(0, getTrailOption("Note", "displayLocation"), 0);
                }
            })
            .put("Portal", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Portal", "displayLocation"), 0);
                }
            })
            .put("RedDust", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("RedDust", "displayLocation"), 0);
                }
            })
            .put("ColoredRedDust", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("ColoredRedDust", "displayLocation"), 0);
                }
            })
            .put("Slime", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Slime", "displayLocation"), 0);
                }
            })
            .put("SnowShovel", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("SnowShovel", "displayLocation"), 0);
                }
            })
            .put("SnowballPoof", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("SnowballPoof", "displayLocation"), 0);
                }
            })
            .put("Spell", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Spell", "displayLocation"), 0);
                }
            })
            .put("Splash", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Splash", "displayLocation"), 0);
                }
            })
            .put("TownAura", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("TownAura", "displayLocation"), 0);
                }
            })
            .put("Wake", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("Wake", "displayLocation"), 0);
                }
            })
            .put("WitchMagic", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("WitchMagic", "displayLocation"), 0);
                }
            })
            .put("Hearts", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                { //cooldown
                    Location loc = player.getLocation().add(0, getTrailOption("Hearts", "displayLocation"), 0);
                }
            })
            .put("EnderSignal", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                { //cooldown
                    Location loc = player.getLocation().add(0, getTrailOption("EnderSignal", "displayLocation"), 0);
                }
            })
            .put("IconCrack", new Consumer<Player>()
            {
                @Override
                public void accept(Player player)
                {
                    Location loc = player.getLocation().add(0, getTrailOption("IconCrack", "displayLocation"), 0);
                }
            })
            .build();
        System.out.println("TRAILS amount: " + TRAILS.size());
    }

    public void loadTrails()
    {
        for (Map.Entry<String, Consumer<Player>> entry : TRAILS.entrySet())
        {
            ItemStack is = loadItem(entry.getKey());
            int slot = main.getConfig().getInt(entry.getKey() + ".item.slot");
            new Trail(entry.getKey(), "trailgui.trail." + entry.getKey(), is, slot, entry.getValue()); //constructor adds object to map in Trail class
        }
    }

    private ItemStack loadItem(String trailName)
    {
        final String matName = main.getConfig().getString(trailName + ".item.type");
        Material material = Material.matchMaterial(matName);
        if (material == null || material == Material.AIR)
        {
            main.getLogger()
                .warning("Incorrect item type in configuration of " + trailName + ": '" + matName + "' is no valid material name!");
            main.getLogger().warning("Because of that, trail " + trailName + " will be a stone in the gui!");
            material = Material.STONE;
        }
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

    private int getTrailOption(String trail, String option)
    {
        return main.getConfig().getInt(trail + ".options." + option);
    }
}

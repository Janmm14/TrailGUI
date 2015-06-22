package ca.jamiesinn.trailgui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ca.jamiesinn.trailgui.commands.TrailCommand;
import ca.jamiesinn.trailgui.commands.TrailGUICommand;
import ca.jamiesinn.trailgui.commands.TrailsCommand;
import ca.jamiesinn.trailgui.files.TrailData;

import com.google.common.collect.Lists;

public class Main
    extends JavaPlugin
{

    private static final boolean useOldSystem = false;
    private InventoryHelper inventoryHelper;

    @Deprecated
    private static Main plugin;

    //@f:off
    @Deprecated public static List<String> trailAngryVillager = new ArrayList<>();
    @Deprecated public static List<String> trailCloud = new ArrayList<>();
    @Deprecated public static List<String> trailCriticals = new ArrayList<>();
    @Deprecated public static List<String> trailDripLava = new ArrayList<>();
    @Deprecated public static List<String> trailDripWater = new ArrayList<>();
    @Deprecated public static List<String> trailEnchantment = new ArrayList<>();
    @Deprecated public static List<String> trailSpark = new ArrayList<>();
    @Deprecated public static List<String> trailFlame = new ArrayList<>();
    @Deprecated public static List<String> trailHappyVillager = new ArrayList<>();
    @Deprecated public static List<String> trailInstantSpell = new ArrayList<>();
    @Deprecated public static List<String> trailLargeSmoke = new ArrayList<>();
    @Deprecated public static List<String> trailLava = new ArrayList<>();
    @Deprecated public static List<String> trailMagicCrit = new ArrayList<>();
    @Deprecated public static List<String> trailMobSpell = new ArrayList<>(); //
    @Deprecated public static List<String> trailMobSpellAmbient = new ArrayList<>();
    @Deprecated public static List<String> trailNote = new ArrayList<>();
    @Deprecated public static List<String> trailPortal = new ArrayList<>();
    @Deprecated public static List<String> trailRedDust = new ArrayList<>();
    @Deprecated public static List<String> trailColoredRedDust = new ArrayList<>();
    @Deprecated public static List<String> trailSlime = new ArrayList<>();
    @Deprecated public static List<String> trailSnowShovel = new ArrayList<>();
    @Deprecated public static List<String> trailSnowballPoof = new ArrayList<>();
    @Deprecated public static List<String> trailSpell = new ArrayList<>();
    @Deprecated public static List<String> trailSplash = new ArrayList<>();
    @Deprecated public static List<String> trailTownAura = new ArrayList<>();
    @Deprecated public static List<String> trailWake = new ArrayList<>();
    @Deprecated public static List<String> trailWitchMagic = new ArrayList<>();
    @Deprecated public static List<String> trailHearts = new ArrayList<>();
    @Deprecated public static List<String> trailEnderSignal = new ArrayList<>();
    @Deprecated public static List<String> trailIconCrack = new ArrayList<>();
    //@f:on

    @Deprecated
    public static Main getPlugin()
    {
        return plugin;
    }

    @Override
    public void onEnable()
    {
        if (useOldSystem)
        {
            enableOld();
        } else
        {
            enableNew();
        }
    }

    private void enableNew()
    {
        getConfig().options().copyDefaults(true);
        convertConfig();
        getConfig().addDefault("gui.name", "&6TrailGUI");
        getConfig().addDefault("gui.noPermLoreLine", "&4You may not use this lore!");
        getConfig().addDefault("gui.noPermLoreInfoAtBottom", false);
        saveConfig();

        new TrailLoader(this).loadTrails();

        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        inventoryHelper = new InventoryHelper(this);
        getServer().getPluginManager().registerEvents(new InventoryListener(main), this);

        if (getConfig().getBoolean("removeTrailOnPlayerHit"))
        {
            getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        }

        enableCommon();

    }

    private void convertConfig()
    {
        addTrailDefault("AngryVillager", "speed", "cooldown");
        addTrailDefault("Cloud");
        addTrailDefault("Criticals", "speed");
        addTrailDefault("DripLava", "speed");
        addTrailDefault("DripWater", "speed");
        addTrailDefault("Enchantment", "speed");
        addTrailDefault("Spark", "speed");
        addTrailDefault("Flame", "speed");
        addTrailDefault("HappyVillager", "speed");
        addTrailDefault("InstantSpell", "speed");
        addTrailDefault("LargeSmoke", "speed");
        addTrailDefault("Lava", "speed");
        addTrailDefault("MagicCrit", "speed");
        addTrailDefault("MobSpell", "speed");
        addTrailDefault("MobSpellAmbient", "speed");
        addTrailDefault("Note", "speed", "cooldown");
        addTrailDefault("Portal", "speed");
        addTrailDefault("RedDust", "speed");
        addTrailDefault("ColoredRedDust", "speed");
        addTrailDefault("Slime", "speed");
        addTrailDefault("SnowShovel", "speed");
        addTrailDefault("SnowballPoof", "speed");
        addTrailDefault("Spell", "speed");
        addTrailDefault("Splash", "speed");
        addTrailDefault("TownAura", "speed");
        addTrailDefault("Wake", "speed");
        addTrailDefault("WitchMagic", "speed");
        addTrailDefault("Hearts", "speed", "cooldown");
        addTrailDefault("EnderSignal", "speed", "cooldown");
        addTrailDefault("IconCrack", "speed");
    }

    private void enableOld()
    {
        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        enableCommon();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        TrailData.createFile();
        TrailData.config = YamlConfiguration.loadConfiguration(TrailData.file);
        Methodes.restoreTrails();
    }

    private void enableCommon()
    {
        plugin = this;

        setExecutorAndCompleter("Trail", new TrailCommand(this));

        getCommand("Trails").setExecutor(new TrailsCommand(this));
        getCommand("TrailGUI").setExecutor(new TrailGUICommand(this));
    }

    private void addTrailDefault(String trailName, String... extraOpts)
    {
        // special case for bug fix of previous version
        if (trailName.equalsIgnoreCase("MagicCrit") && "water_bottle"
            .equalsIgnoreCase(getConfig().getString(trailName + "-itemType")))
        {
            getConfig().addDefault(trailName + ".item.data", "potion");
        } else
        {
            addTrailDefaultCopy(trailName + ".item.type", trailName + "-itemType");
        }
        getConfig().addDefault(trailName + ".item.data", 0); //new option so no copy from old
        addTrailDefaultCopy(trailName + ".item.slot", trailName + "-inventorySlot");
        getConfig().addDefault(trailName + ".item.page", 0);
        addTrailDefaultCopy(trailName + ".item.name", trailName + "-itemName");
        getConfig().addDefault(trailName + ".item.lore", getOldLoreCfg(trailName));

        addTrailDefaultCopy(trailName + ".options.displayLocation", trailName + "-displayLocation");
        addTrailDefaultCopy(trailName + ".options.amount", trailName + "-amount");
        addTrailDefaultCopy(trailName + ".options.range", trailName + "-range");

        if (extraOpts != null)
        {
            for (String opt : extraOpts)
            {
                addTrailDefaultCopy(trailName + ".options." + opt, getConfig().getString(trailName + "-" + opt));
            }
        }
    }

    private void addTrailDefaultCopy(String newStr, String old)
    {
        getConfig().addDefault(newStr, getConfig().getString(old));
    }

    private List<String> getOldLoreCfg(String trailName)
    {
        if (getConfig().getBoolean(trailName + "-loreEnabled"))
        {
            String loreLine1 = getConfig().getString(trailName + "-loreLineOne", "");
            String loreLine2 = getConfig().getString(trailName + "-loreLineTwo", "");
            String loreLine3 = getConfig().getString(trailName + "-loreLineThree", "");
        }
        return Lists.newArrayList();
    }

    private <T extends CommandExecutor & TabCompleter> T setExecutorAndCompleter(String cmdName, T handler)
    {
        final PluginCommand cmd = getCommand(cmdName);
        cmd.setExecutor(handler);
        cmd.setTabCompleter(handler);
        return handler;
    }

    @Override
    public void onDisable()
    {
        if (useOldSystem)
        {
            Methodes.saveTrails();
        } else
        {
            //TODO disable on new system
        }
    }

    public InventoryHelper getInventoryHelper()
    {
        return inventoryHelper;
    }
}

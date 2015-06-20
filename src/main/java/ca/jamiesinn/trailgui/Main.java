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

public class Main
    extends JavaPlugin
{

    private static final boolean useOldSystem = false;

    @Deprecated
    public static Main plugin;

    public static List<String> trailAngryVillager = new ArrayList<>();
    public static List<String> trailCloud = new ArrayList<>();
    public static List<String> trailCriticals = new ArrayList<>();
    public static List<String> trailDripLava = new ArrayList<>();
    public static List<String> trailDripWater = new ArrayList<>();
    public static List<String> trailEnchantment = new ArrayList<>();
    public static List<String> trailSpark = new ArrayList<>();
    public static List<String> trailFlame = new ArrayList<>();
    public static List<String> trailHappyVillager = new ArrayList<>();
    public static List<String> trailInstantSpell = new ArrayList<>();
    public static List<String> trailLargeSmoke = new ArrayList<>();
    public static List<String> trailLava = new ArrayList<>();
    public static List<String> trailMagicCrit = new ArrayList<>();
    public static List<String> trailMobSpell = new ArrayList<>(); //
    public static List<String> trailMobSpellAmbient = new ArrayList<>();
    public static List<String> trailNote = new ArrayList<>();
    public static List<String> trailPortal = new ArrayList<>();
    public static List<String> trailRedDust = new ArrayList<>();
    public static List<String> trailColoredRedDust = new ArrayList<>();
    public static List<String> trailSlime = new ArrayList<>();
    public static List<String> trailSnowShovel = new ArrayList<>();
    public static List<String> trailSnowballPoof = new ArrayList<>();
    public static List<String> trailSpell = new ArrayList<>();
    public static List<String> trailSplash = new ArrayList<>();
    public static List<String> trailTownAura = new ArrayList<>();
    public static List<String> trailWake = new ArrayList<>();
    public static List<String> trailWitchMagic = new ArrayList<>();
    public static List<String> trailHearts = new ArrayList<>();
    public static List<String> trailEnderSignal = new ArrayList<>();
    public static List<String> trailIconCrack = new ArrayList<>();

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
        new TrailLoader(this).loadTrails();
        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        //TODO
        enableCommon();
    }

    private void enableOld()
    {
        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        enableCommon();

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

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

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
        if (useOldSystem) {
            Methodes.saveTrails();
        } else {
            //TODO
        }
    }
}

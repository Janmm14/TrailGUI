package ca.jamiesinn.trailgui.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ca.jamiesinn.trailgui.Main;
import ca.jamiesinn.trailgui.files.TrailData;

public class TrailGUICommand
    implements CommandExecutor
{
    private Main main;

    public TrailGUICommand(Main main)
    {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage(TrailCommand.PREFIX + ChatColor.GREEN + "Available commands:");
            sender.sendMessage(ChatColor.GREEN + "/TrailGUI ReloadConfigs");
            sender.sendMessage(ChatColor.GREEN + "/TrailGUI Version");
            return true;
        }
        if (args[0].equalsIgnoreCase("reloadconfigs") || args[0].equalsIgnoreCase("reloadconfig"))
        {
            if (!sender.hasPermission("trailgui.commands.reloadconfigs"))
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    main.getConfig().getString("Commands-denyPermissionMessage")));
                return true;
            }
            //TODO rework these methods
            TrailData.reloadConfig();
            TrailData.saveConfig();

            main.reloadConfig();
            main.saveConfig();

            sender
                .sendMessage(TrailCommand.PREFIX + ChatColor.GREEN + "Successfully reloaded all config files.");
            return true;
        }
        if (args[0].equalsIgnoreCase("version"))
        {
            if (!sender.hasPermission("trailgui.commands.version"))
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    main.getConfig().getString("Commands-denyPermissionMessage")));
                return true;
            }
            sender.sendMessage(TrailCommand.PREFIX + ChatColor.GREEN + "Version 3.1 created by "
                + ChatColor.BOLD + "Coder_M, JamieSinn and Janmm14" + ChatColor.GREEN + ".");
            return true;
        }
        sender.sendMessage(TrailCommand.PREFIX + ChatColor.RED + "Subcommand " + args[0] + " not found!");
        sender.sendMessage(TrailCommand.PREFIX + ChatColor.GREEN + "Available commands:");
        sender.sendMessage(ChatColor.GREEN + "/TrailGUI ReloadConfigs");
        sender.sendMessage(ChatColor.GREEN + "/TrailGUI Version");
        return true;
    }
}

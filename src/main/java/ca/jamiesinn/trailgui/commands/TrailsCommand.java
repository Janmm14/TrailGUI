package ca.jamiesinn.trailgui.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.jamiesinn.trailgui.Main;

public class TrailsCommand
    implements CommandExecutor
{
    private Main main;

    public TrailsCommand(Main main)
    {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(TrailCommand.PREFIX + ChatColor.DARK_RED + "Only players can perform this command.");
            return true;
        }
        Player player = (Player) sender;
        for (String string : main.getConfig().getStringList("disabledWorlds"))
        {
            if (string.equals(player.getWorld().getName()))
            {
                player
                    .sendMessage(TrailCommand.PREFIX + ChatColor.RED + "You cannot use this command in this world.");
                return true;
            }
        }
        if (!player.hasPermission("trailgui.commands.trails"))
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                main.getConfig().getString("Commands-denyPermissionMessage")));
            return true;
        }
        main.getInventoryHelper().openInventory(player);

        return true;
    }
}

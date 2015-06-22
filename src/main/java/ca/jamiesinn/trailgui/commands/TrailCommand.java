package ca.jamiesinn.trailgui.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import ca.jamiesinn.trailgui.Main;
import ca.jamiesinn.trailgui.Trail;

import com.google.common.collect.Lists;

public class TrailCommand
    implements CommandExecutor, TabCompleter
{

    public static final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "TrailGUI" + ChatColor.DARK_GRAY + "] ";
    private Main main;

    public TrailCommand(Main main)
    {
        this.main = main;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length == 0 || args.length > 2)
        {
            return null;
        }
        List<String> tcTrails = Lists.newArrayListWithExpectedSize(Trail.getTrails().size() + 1);
        tcTrails.addAll(Trail.getTrailsMap().keySet());
        tcTrails.add("ClearAll");

        if (args.length == 1)
        {
            if (args[0].isEmpty())
            {
                for (String trail : tcTrails)
                {
                    if (!trail.toLowerCase().startsWith(args[0].toLowerCase()))
                    {
                        tcTrails.remove(trail);
                    }
                }
                Collections.sort(tcTrails);
                return tcTrails;
            } else
            {
                Collections.sort(tcTrails);
                return tcTrails;
            }
        }
        //now args.length has to be 2
        List<String> tcNames = new ArrayList<>();
        final String inputName = args[1];
        for (Player player : Bukkit.getOnlinePlayers())
        {
            final String name = player.getName();
            if (name.startsWith(inputName))
            {
                tcNames.add(name);
            }
        }
        Collections.sort(tcNames);
        return tcNames;
    }

    public boolean onCommand(CommandSender cs, Command cmd, String alias, String[] args)
    {
        if (args.length == 0)
        {
            cs.sendMessage(PREFIX + ChatColor.GREEN + "Available commands:");
            if (cs instanceof Player)
            {
                cs.sendMessage(ChatColor.GREEN + "/trail <TrailName>");
            } else
            {
                cs.sendMessage(ChatColor.GREEN + "/trail <TrailName>" + ChatColor.RED + " Only usable by players!");
            }
            cs.sendMessage(ChatColor.GREEN + "/trail <TrailName> <PlayerName>");
            return true;
        }
        if (args.length == 1)
        {
            if (!(cs instanceof Player))
            {
                cs.sendMessage(PREFIX + ChatColor.DARK_RED + "Only players can perform this command.");
                return true;
            }

            Player player = (Player) cs;

            for (String world : main.getConfig().getStringList("disabledWorlds"))
            {
                if (world.equalsIgnoreCase(player.getWorld().getName()))
                {
                    player.sendMessage(PREFIX + ChatColor.RED + "You cannot use this command in this world.");
                    return true;
                }
            }

            final String givenTrailName = args[0];
            final UUID uuid = player.getUniqueId();
            Trail trail = Trail.matchTrail(givenTrailName);
            if (trail == null)
            {
                player.sendMessage(PREFIX + ""); //TODO message
                return true;
            }
            if (trail.hasTrail(uuid))
            {
                trail.removeTrail(uuid);
                //TODO message
                return true;
            }
            if (player.hasPermission("trailgui.oneTrailAtATime"))
            {
                Trail.removeTrails(uuid);
            }
            trail.addTrail(uuid);
            //TODO message
            return true;
        }
        if (args.length == 2)
        {
            Player player = Bukkit.getPlayerExact(args[1]);

            for (String world : main.getConfig().getStringList("disabledWorlds"))
            {
                if (world.equalsIgnoreCase(player.getWorld().getName()))
                {
                    player.sendMessage(PREFIX + ChatColor.RED + "You cannot use this command in this world.");
                    return true;
                }
            }

            final String givenTrailName = args[0];
            final UUID uuid = player.getUniqueId();
            Trail trail = Trail.matchTrail(givenTrailName);
            if (trail == null)
            {
                player.sendMessage(PREFIX + ""); //TODO message sender
                return true;
            }
            if (trail.hasTrail(uuid))
            {
                trail.removeTrail(uuid);
                //TODO message sender and player
                return true;
            }
            if (player.hasPermission("trailgui.oneTrailAtATime"))
            {
                Trail.removeTrails(uuid);
            }
            trail.addTrail(uuid);
            //TODO message sender and player
            return true;
        }
        return false;
    }

}

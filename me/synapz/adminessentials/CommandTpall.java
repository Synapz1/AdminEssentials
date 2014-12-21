package me.synapz.adminessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTpall
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((cmd.getName().equalsIgnoreCase("tpall")) && (!(sender instanceof Player))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "This command is only available for players!");
            } else if (args.length >= 1) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /tpall");
            }
        }
        if ((cmd.getName().equalsIgnoreCase("tpall")) && ((sender instanceof Player))) {
            Player player = (Player)sender;
            if (!player.hasPermission("adminessentials.tpall")) {
                player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
            }
            else if (args.length == 0) {
                Location playerLocation = player.getLocation();
                player.sendMessage(ChatColor.GOLD + "All players have been teleporter to you!");
                for (Player onlinePlayers : Bukkit.getOnlinePlayers())
                    onlinePlayers.teleport(playerLocation);
            }
            else if (args.length >= 1) {
                player.sendMessage(ChatColor.RED + "To many arguments!");
                player.sendMessage(ChatColor.RED + "Usage: /tpall");
            }

        }

        return false;
    }
}
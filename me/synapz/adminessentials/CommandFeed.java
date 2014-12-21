package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("feed"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /feed <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                } else {
                    targetPlayer.setFoodLevel(20);
                    targetPlayer.sendMessage(ChatColor.GOLD + "You were fead!");
                    sender.sendMessage(ChatColor.GOLD + "You fead " + ChatColor.RED + targetPlayer.getName());
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /feed <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("feed")) {
                if (player.hasPermission("adminessentials.feed")) {
                    if (args.length == 0) {
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.GOLD + "You fead " +
                                ChatColor.RED + "yourself!");
                    }
                }
                else if (args.length == 0) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }

                if (!player.hasPermission("adminessentials.feed.others")) {
                    if (args.length >= 1) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 1) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.setFoodLevel(20);
                        targetPlayer.sendMessage(ChatColor.GOLD + "You were fead!");
                        player.sendMessage(ChatColor.GOLD + "You fead " + ChatColor.RED + targetPlayer.getName());
                    }
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /feed <player>");
                }
            }
        }

        return false;
    }
}
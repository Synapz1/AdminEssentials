package me.synapz.adminessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        String msg = " ";
        for (int i = 1; i < args.length; i++) {
            msg = msg + args[i] + " ";
        }
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("kick")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /kick <player> [message]");
                }
                else if (args.length == 1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.kickPlayer("Kicked from server.");
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " kicked " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " for Kicked from server.");
                    }

                }
                else if (args.length >= 2) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    targetPlayer.kickPlayer(msg);
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " kicked " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " for" + msg);
                }

            }
            else if (cmd.getName().equalsIgnoreCase("kickall")) {
                if (args.length == 0) {
                    Bukkit.broadcastMessage(ChatColor.RED + "CONSOLE" + ChatColor.GOLD + " kicked " + ChatColor.RED + "All players" + ChatColor.GOLD + " for " + ChatColor.RED + "Kicked from server!");
                    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.kickPlayer("Kicked from the server!");
                    }
                }
                else if (args.length >= 1) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /kickall");
                }
            }
        }
        else if ((sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("kick")) {
                Player player = (Player)sender;
                if (player.hasPermission("adminessentials.kick")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Not enough arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /kick <player> [message]");
                    }
                    else if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        } else {
                            targetPlayer.kickPlayer("Kicked from server.");
                            Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " kicked " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " for Kicked from server.");
                        }
                    }
                    else if (args.length >= 2) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        targetPlayer.kickPlayer(msg);
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " kicked " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " for" + msg);
                    }
                }
                else player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");

            }

            if (cmd.getName().equalsIgnoreCase("kickall")) {
                Player player = (Player)sender;
                if (!player.hasPermission("adminessentials.kickall")) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
                else if (args.length == 0) {
                    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.kickPlayer("Kicked from the server!");
                    }
                }
                else if (args.length >= 1) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /kickall");
                }
            }
        }

        return false;
    }
}
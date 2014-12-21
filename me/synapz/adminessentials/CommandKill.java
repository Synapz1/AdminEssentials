package me.synapz.adminessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKill
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("kill")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /kill <player>");
                }
                else if (args.length == 1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " killed you!");
                        sender.sendMessage(ChatColor.GOLD + "You killed " + ChatColor.RED + targetPlayer.getName());
                        targetPlayer.setHealth(0);
                    }
                }
                else if (args.length >= 2) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /kill <player>");
                }
            }
            else if (cmd.getName().equalsIgnoreCase("killall")) {
                if (args.length == 0) {
                    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.setHealth(0);
                    }

                    sender.sendMessage(ChatColor.GOLD + "All Players Killed!");
                }
                else if (args.length >= 1) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /killall");
                }
            }
        }
        else if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("kill")) {
                if (!player.hasPermission("adminessentials.kill")) {
                    player.sendMessage(ChatColor.DARK_RED +
                            "You don't have access to that command!");
                }
                else if (args.length == 1) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.sendMessage(ChatColor.RED +
                                player.getName() + ChatColor.GOLD +
                                " killed you!");
                        player.sendMessage(ChatColor.GOLD + "You killed " +
                                ChatColor.RED + targetPlayer.getName());
                        targetPlayer.setHealth(0);
                    }
                } else if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /kill <player>");
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /kill <player>");
                }

            }
            else if (cmd.getName().equalsIgnoreCase("killall")) {
                if (!player.hasPermission("adminessentials.killall")) {
                    player.sendMessage(ChatColor.DARK_RED +
                            "You don't have access to that command!");
                }
                else if (args.length == 0) {
                    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.setHealth(0);
                    }
                    player.sendMessage(ChatColor.GOLD + "All Players Killed!");
                }
                else if (args.length >= 1) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /killall");
                }

            }

        }

        return false;
    }
}
package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("tp")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /tp <player> <player>");
                }
                else if (args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /tp <player> <player>");
                }
                else if (args.length == 2) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    Player targetPlayer1 = sender.getServer().getPlayer(args[1]);

                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        Location targetPlayer1Location = targetPlayer1
                                .getLocation();
                        targetPlayer.teleport(targetPlayer1Location);
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED +
                                targetPlayer.getName() + ChatColor.GOLD +
                                " was teleported to " + ChatColor.RED +
                                targetPlayer1.getName());
                    }
                }
                else if (args.length >= 2) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /tp <player> <player>");
                }
            } else if ((cmd.getName().equalsIgnoreCase("tphere")) &&
                    (args.length >= 0)) {
                sender.sendMessage(ChatColor.RED + "Console canno't teleport players to them!");
                sender.sendMessage(ChatColor.RED + "Use: /tp <player> <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("tp")) {
                if (!player.hasPermission("adminessentials.tp")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /tp <player>");
                }
                else if (args.length == 1) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        Location targetPlayerLocation = targetPlayer.getLocation();
                        player.teleport(targetPlayerLocation);
                        player.sendMessage(ChatColor.GOLD + "You teleported to " +
                                ChatColor.RED + targetPlayer.getName());
                    }
                }

                if (!player.hasPermission("adminessentials.tp.others")) {
                    if (args.length >= 1) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 2) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    Player targetPlayer1 = player.getServer().getPlayer(args[1]);

                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        Location targetPlayer1Location = targetPlayer1
                                .getLocation();
                        targetPlayer.teleport(targetPlayer1Location);
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED +
                                targetPlayer.getName() + ChatColor.GOLD +
                                " was teleported to " + ChatColor.RED +
                                targetPlayer1.getName());
                    }
                }
                else if (args.length >= 3) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /tp <player>");
                }

            }
            else if (cmd.getName().equalsIgnoreCase("tphere")) {
                if (player.hasPermission("adminessentials.tphere")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED +
                                "You need to specify a player!");
                    } else if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        } else {
                            Location playerLocation = player.getLocation();
                            targetPlayer.teleport(playerLocation);
                            player.sendMessage(ChatColor.GOLD + "You teleported " +
                                    ChatColor.RED + targetPlayer.getName() +
                                    ChatColor.GOLD + " to you.");
                            targetPlayer.sendMessage(ChatColor.RED +
                                    player.getName() + ChatColor.GOLD +
                                    " has teleported you to them.");
                        }
                    } else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED +
                                "Usage: /tphere <player>");
                    }
                }
                else player.sendMessage(ChatColor.DARK_RED +
                        "You don't have access to that command!");
            }
        }

        return false;
    }
}
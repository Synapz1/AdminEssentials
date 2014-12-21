package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBurn
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("ext")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enoguh arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /ext <player>");
                }
                else if (args.length == 1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.setFireTicks(0);
                        targetPlayer.sendMessage(ChatColor.RED +
                                sender.getName() + ChatColor.GOLD +
                                " extinguished fire from you!");
                        sender.sendMessage(ChatColor.GOLD +
                                "Extinguished fire from " + ChatColor.RED +
                                targetPlayer.getName());
                    }
                }
                else if (args.length >= 2) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /ext <player>");
                }
            }
            else if (cmd.getName().equalsIgnoreCase("burn")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enoguh arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /burn <player>");
                }
                else if (args.length == 1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    } else {
                        targetPlayer.setFireTicks(1000);
                        targetPlayer.sendMessage(ChatColor.RED +
                                sender.getName() + ChatColor.GOLD +
                                " set you on fire!");
                        sender.sendMessage(ChatColor.GOLD + "You set " +
                                ChatColor.RED + targetPlayer.getName() +
                                ChatColor.GOLD + " on fire!");
                    }
                }
                else if (args.length >= 2) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /burn <player>");
                }

            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("ext")) {
                if (!player.hasPermission("adminessentials.ext")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 0) {
                    player.setFireTicks(0);
                    player.sendMessage(ChatColor.GOLD +
                            "You extinguished fire from " + ChatColor.RED +
                            player.getName());
                }

                if (!player.hasPermission("adminessentials.ext.others")) {
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
                        targetPlayer.setFireTicks(0);
                        targetPlayer.sendMessage(ChatColor.RED +
                                player.getName() + ChatColor.GOLD +
                                " extinguished fire from you!");
                        player.sendMessage(ChatColor.GOLD +
                                "Extinguished fire from " + ChatColor.RED +
                                targetPlayer.getName());
                    }
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /ext <player>");
                }

            }
            else if (cmd.getName().equalsIgnoreCase("burn")) {
                if (!player.hasPermission("adminessentials.burn")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 0) {
                    player.setFireTicks(1000);
                    player.sendMessage(ChatColor.GOLD + "You set " +
                            ChatColor.RED + player.getName() +
                            ChatColor.GOLD + " on fire!");
                }

                if (!player.hasPermission("adminessentials.burn.others")) {
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
                        targetPlayer.setFireTicks(1000);
                        targetPlayer.sendMessage(ChatColor.RED +
                                player.getName() + ChatColor.GOLD +
                                " set you on fire!");
                        player.sendMessage(ChatColor.GOLD + "You set " +
                                ChatColor.RED + targetPlayer.getName() +
                                ChatColor.GOLD + " on fire!");
                    }
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /burn <player>");
                }
            }

        }

        return false;
    }
}
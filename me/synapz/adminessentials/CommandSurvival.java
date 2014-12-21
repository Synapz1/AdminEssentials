package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSurvival
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("gms"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /gms <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                } else {
                    targetPlayer.setGameMode(GameMode.SURVIVAL);
                    targetPlayer.sendMessage(ChatColor.RED +
                            sender.getName() + ChatColor.GOLD +
                            " set you to survival mode!");
                    sender.sendMessage(ChatColor.GOLD + "You set " +
                            ChatColor.RED + targetPlayer.getName() +
                            ChatColor.GOLD + " gamemode to survival!");
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /gms <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("gms")) {
                if (!player.hasPermission("adminessentials.survival")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.DARK_RED +
                                "You don't have access to that command!");
                    }
                }
                else if (args.length == 0) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GOLD + "You set " +
                            ChatColor.RED + player.getName() +
                            ChatColor.GOLD + " to survival mode!");
                }

                if (!player.hasPermission("adminessentials.survival.others")) {
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
                        targetPlayer.setGameMode(GameMode.SURVIVAL);
                        targetPlayer.sendMessage(ChatColor.RED +
                                player.getName() + ChatColor.GOLD +
                                " set you to survival mode!");
                        player.sendMessage(ChatColor.GOLD + "You set " +
                                ChatColor.RED + targetPlayer.getName() +
                                ChatColor.GOLD + " gamemode to survival!");
                    }
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /gms <player>");
                }
            }

        }

        return false;
    }
}
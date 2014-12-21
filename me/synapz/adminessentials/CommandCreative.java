package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreative
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("gmc"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /gmc <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                } else {
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    targetPlayer.sendMessage(ChatColor.RED +
                            sender.getName() + ChatColor.GOLD +
                            " set you to creative mode!");
                    sender.sendMessage(ChatColor.GOLD + "You set " +
                            ChatColor.RED + targetPlayer.getName() +
                            ChatColor.GOLD + " gamemode to creative!");
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /gmc <player>");
            }

        }

        if (((sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("gmc"))) {
            Player player = (Player)sender;
            if (!player.hasPermission("adminessentials.creative")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.DARK_RED +
                            "You don't have access to that command!");
                }
            }
            else if (args.length == 0) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(ChatColor.GOLD + "You set " +
                        ChatColor.RED + player.getName() +
                        ChatColor.GOLD + " to creative mode!");
            }

            if (!player.hasPermission("adminessentials.creative.others")) {
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
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    targetPlayer.sendMessage(ChatColor.RED +
                            player.getName() + ChatColor.GOLD +
                            " set you to creative mode!");
                    player.sendMessage(ChatColor.GOLD + "You set " +
                            ChatColor.RED + targetPlayer.getName() +
                            ChatColor.GOLD + " gamemode to creative!");
                }
            } else if (args.length >= 2) {
                player.sendMessage(ChatColor.RED + "To many arguments!");
                player.sendMessage(ChatColor.RED + "Usage: /gmc <player>");
            }

        }

        return false;
    }
}
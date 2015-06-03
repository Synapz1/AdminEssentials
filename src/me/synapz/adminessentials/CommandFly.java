package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("fly"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /fly <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                }
                else if (targetPlayer.getAllowFlight()) {
                    targetPlayer.setAllowFlight(false);
                    targetPlayer.sendMessage(ChatColor.GOLD + "Fly mode was disabled!");
                    sender.sendMessage(ChatColor.GOLD + "Fly mode was disabled for " + ChatColor.RED + targetPlayer.getName());
                } else {
                    targetPlayer.setAllowFlight(true);
                    targetPlayer.sendMessage(ChatColor.GOLD + "Fly mode was enabled!");
                    sender.sendMessage(ChatColor.GOLD + "Fly mode was enabled for " + ChatColor.RED + targetPlayer.getName());
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /fly <player>");
            }
        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (player.hasPermission("adminessentials.fly")) {
                    if (args.length == 0)
                        if (player.getAllowFlight()) {
                            player.setAllowFlight(false);
                            player.sendMessage(ChatColor.GOLD + "Fly mode disabled for " + ChatColor.RED + player.getName());
                        } else {
                            player.setAllowFlight(true);
                            player.sendMessage(ChatColor.GOLD + "Fly mode enabled for " + ChatColor.RED + player.getName());
                        }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
                if (player.hasPermission("adminessentials.fly.others")) {
                    if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        }
                        else if (targetPlayer.getAllowFlight()) {
                            targetPlayer.setAllowFlight(false);
                            targetPlayer.sendMessage(ChatColor.GOLD + "Fly mode was disabled!");
                            player.sendMessage(ChatColor.GOLD + "Fly mode was disabled for " + ChatColor.RED + targetPlayer.getName());
                        } else {
                            targetPlayer.setAllowFlight(true);
                            targetPlayer.sendMessage(ChatColor.GOLD + "Fly mode was enabled!");
                            player.sendMessage(ChatColor.GOLD + "Fly mode was enabled for " + ChatColor.RED + targetPlayer.getName());
                        }
                    }
                    else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /fly <player>");
                    }
                }
                else if (args.length >= 1) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to this command!");
                }
            }
        }

        return false;
    }
}
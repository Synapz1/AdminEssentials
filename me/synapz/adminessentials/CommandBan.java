package me.synapz.adminessentials;

import java.util.ArrayList;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandBan
        implements Listener, CommandExecutor
{
    ArrayList<String> bannedPlayers = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        String msg = " ";
        for (int i = 1; i < args.length; i++) {
            msg = msg + args[i] + " ";
        }
        String bannedServer = "Banned from server!";
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("ban")) {
                BanList ban = sender.getServer().getBanList(BanList.Type.NAME);
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /ban <player> [message]");
                }
                else if (args.length == 1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        OfflinePlayer targetPlayerBanned = sender.getServer().getOfflinePlayer(args[0]);
                        ban.addBan(targetPlayerBanned.getName(), bannedServer, null, sender.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned.getName() + ChatColor.GOLD + " for " + "Banned from server!");
                    } else {
                        Player targetPlayerBanned = sender.getServer().getPlayer(args[0]);
                        targetPlayerBanned.kickPlayer(msg);
                        ban.addBan(targetPlayerBanned.getName(), bannedServer, null, sender.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned.getName() + ChatColor.GOLD + " for " + "Banned from server!");
                    }
                }
                else if (args.length >= 2) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        OfflinePlayer targetPlayerBanned1 = sender.getServer().getOfflinePlayer(args[0]);
                        ban.addBan(targetPlayerBanned1.getName(), msg, null, sender.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned1.getName() + ChatColor.GOLD + " for" + msg);
                    } else {
                        Player targetPlayerBanned1 = sender.getServer().getPlayer(args[0]);
                        targetPlayerBanned1.kickPlayer(msg);
                        ban.addBan(targetPlayerBanned1.getName(), msg, null, sender.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned1.getName() + ChatColor.GOLD + " for" + msg);
                    }
                }
            }
            else if (cmd.getName().equalsIgnoreCase("unban")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /unban <player>");
                }
                else if (args.length == 1) {
                    OfflinePlayer targetPlayer = sender.getServer().getOfflinePlayer(args[0]);
                    if (targetPlayer.isBanned()) {
                        targetPlayer.setBanned(false);
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + sender.getName() + ChatColor.GOLD + " unbanned " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + "!");
                    } else {
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " isn't banned!");
                    }
                }
                else if (args.length >= 2) {
                    sender.sendMessage(ChatColor.RED + "To many arguments!");
                    sender.sendMessage(ChatColor.RED + "Usage: /unban <player>");
                }

            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("ban")) {
                BanList ban = player.getServer().getBanList(BanList.Type.NAME);
                if (!player.hasPermission("adminessentials.ban")) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
                else if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /ban <player> [message]");
                }
                else if (args.length == 1) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        OfflinePlayer targetPlayerBanned = player.getServer().getOfflinePlayer(args[0]);
                        ban.addBan(targetPlayerBanned.getName(), bannedServer, null, player.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned.getName() + ChatColor.GOLD + " for " + "Banned from server!");
                    } else {
                        Player targetPlayerBanned = player.getServer().getPlayer(args[0]);
                        targetPlayerBanned.kickPlayer(bannedServer);
                        ban.addBan(targetPlayerBanned.getName(), bannedServer, null, player.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned.getName() + ChatColor.GOLD + " for " + "Banned from server!");
                    }
                }
                else if (args.length >= 2) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        OfflinePlayer targetPlayerBanned1 = player.getServer().getOfflinePlayer(args[0]);
                        ban.addBan(targetPlayerBanned1.getName(), msg, null, player.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned1.getName() + ChatColor.GOLD + " for" + msg);
                    } else {
                        Player targetPlayerBanned1 = player.getServer().getPlayer(args[0]);
                        targetPlayerBanned1.kickPlayer(msg);
                        ban.addBan(targetPlayerBanned1.getName(), msg, null, player.getName());
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " banned " + ChatColor.RED + targetPlayerBanned1.getName() + ChatColor.GOLD + " for" + msg);
                    }

                }

            }
            else if (cmd.getName().equalsIgnoreCase("unban")) {
                if (player.hasPermission("adminessentials.ban")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Not enough arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /unban <player>");
                    }
                    else if (args.length == 1) {
                        OfflinePlayer targetPlayer = player.getServer().getOfflinePlayer(args[0]);
                        if (targetPlayer.isBanned()) {
                            targetPlayer.setBanned(false);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.RED + player.getName() + ChatColor.GOLD + " unbanned " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + "!");
                        } else {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " isn't banned!");
                        }

                    }
                    else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /unban <player>");
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
            }
        }
        return false;
    }
}
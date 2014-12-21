package me.synapz.adminessentials;

import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandMute
        implements CommandExecutor, Listener
{
    ArrayList<UUID> mute = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("mute"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /mute <player>");
            } else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                }
                else if (this.mute.contains(targetPlayer.getUniqueId())) {
                    this.mute.remove(targetPlayer.getUniqueId());
                    sender.sendMessage(ChatColor.DARK_AQUA + "You umuted " + ChatColor.RED + targetPlayer.getName());
                    targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You were unmuted!");
                } else {
                    this.mute.add(targetPlayer.getUniqueId());
                    sender.sendMessage(ChatColor.DARK_AQUA + "You muted " + ChatColor.RED + targetPlayer.getName());
                    targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You were muted!");
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /mute <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("mute")) {
                if (player.hasPermission("adminessentials.mute")) {
                    if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        }
                        else if (this.mute.contains(targetPlayer.getUniqueId())) {
                            this.mute.remove(targetPlayer.getUniqueId());
                            player.sendMessage(ChatColor.DARK_AQUA + "You umuted " + ChatColor.RED + targetPlayer.getName());
                            targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You were unmuted!");
                        } else {
                            this.mute.add(targetPlayer.getUniqueId());
                            player.sendMessage(ChatColor.DARK_AQUA + "You muted " + ChatColor.RED + targetPlayer.getName());
                            targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You were muted!");
                        }
                    }
                    else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /mute <player>");
                    }
                    else if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Not enough arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /mute <player>");
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
            }

        }

        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (this.mute.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You were muted!");
        }
    }
}
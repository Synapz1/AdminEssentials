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
import org.bukkit.event.entity.EntityDamageEvent;

public class CommandGod
        implements CommandExecutor, Listener
{
    ArrayList<UUID> godPlayers = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /god <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                }
                else if (this.godPlayers.contains(targetPlayer.getUniqueId())) {
                    this.godPlayers.remove(targetPlayer.getUniqueId());
                    targetPlayer.sendMessage(ChatColor.GOLD + "God mode disabled!");
                    sender.sendMessage(ChatColor.GOLD + "God mode disabled for " + ChatColor.RED + targetPlayer.getName());
                } else {
                    this.godPlayers.add(targetPlayer.getUniqueId());
                    targetPlayer.sendMessage(ChatColor.GOLD + "God mode enabled!");
                    sender.sendMessage(ChatColor.GOLD + "God mode enabled for " + ChatColor.RED + targetPlayer.getName());
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /god <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (commandLabel.equalsIgnoreCase("god"))
            {
                if (player.hasPermission("adminessentials.god"))
                {
                    if (args.length == 0) {
                        if (this.godPlayers.contains(player.getUniqueId())) {
                            this.godPlayers.remove(player.getUniqueId());
                            player.sendMessage(ChatColor.GOLD + "God mode is disabled for " + ChatColor.RED + player.getName());
                        } else {
                            this.godPlayers.add(player.getUniqueId());
                            player.sendMessage(ChatColor.GOLD + "God mode is enabled for " + ChatColor.RED + player.getName());
                        }
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
                if (player.hasPermission("adminessentials.god.others")) {
                    if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        }
                        else if (this.godPlayers.contains(targetPlayer.getUniqueId())) {
                            this.godPlayers.remove(targetPlayer.getUniqueId());
                            targetPlayer.sendMessage(ChatColor.GOLD + "God mode disabled!");
                            player.sendMessage(ChatColor.GOLD + "God mode disabled for " + ChatColor.RED + targetPlayer.getName());
                        } else {
                            this.godPlayers.add(targetPlayer.getUniqueId());
                            targetPlayer.sendMessage(ChatColor.GOLD + "God mode enabled!");
                            player.sendMessage(ChatColor.GOLD + "God mode enabled for " + ChatColor.RED + targetPlayer.getName());
                        }
                    }
                    else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /god <player>");
                    }
                }
                else if (args.length >= 1) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to this command!");
                }
            }

        }

        return false;
    }

    @EventHandler
    public void onPlayerMove(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player)) {
            Player player = (Player)event.getEntity();
            if (this.godPlayers.contains(player.getUniqueId()))
                event.setCancelled(true);
        }
    }
}
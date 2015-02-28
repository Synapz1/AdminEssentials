package me.synapz.adminessentials;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CommandFreeze implements Listener, CommandExecutor

{

    ArrayList<UUID> frozenPlayers = new ArrayList<UUID>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
            }
            else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                }

                else if(this.frozenPlayers.contains(targetPlayer.getUniqueId())) {
                    this.frozenPlayers.remove(targetPlayer.getUniqueId());
                    targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You have been unfrozen!");
                    sender.sendMessage(ChatColor.DARK_AQUA + "You unfroze " + ChatColor.RED + targetPlayer.getName());
                }else{
                    this.frozenPlayers.add(targetPlayer.getUniqueId());
                    targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You have been frozen!");
                    sender.sendMessage(ChatColor.DARK_AQUA + "You froze " + ChatColor.RED + targetPlayer.getName());

                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
            }
        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("freeze")) {
                if (!player.hasPermission("adminessentials.freeze")) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to this command!");
                }
                else if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
                } else if (args.length == 1) {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                    }
                    if(this.frozenPlayers.contains(targetPlayer.getUniqueId())) {
                        this.frozenPlayers.remove(targetPlayer.getUniqueId());
                        targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You have been unfrozen!");
                        sender.sendMessage(ChatColor.DARK_AQUA + "You unfroze " + ChatColor.RED + targetPlayer.getName());
                    }else{
                        this.frozenPlayers.add(targetPlayer.getUniqueId());
                        targetPlayer.sendMessage(ChatColor.DARK_AQUA + "You have been frozen!");
                        sender.sendMessage(ChatColor.DARK_AQUA + "You froze " + ChatColor.RED + targetPlayer.getName());

                    }
                } else if (args.length >= 2) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
                }

            }



            //TODO: make a unfreeze for all players
            else if(cmd.getName().equalsIgnoreCase("freezeall")){
                if(player.hasPermission("adminessentials.freezeall")) {
                    if (args.length == 0) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.RED + "You" + ChatColor.GOLD + " have been frozen.");
                            this.frozenPlayers.add(players.getUniqueId());
                        }
                        player.sendMessage(ChatColor.GOLD + "You froze everyone.");
                    }
                    else if (args.length >= 1){
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /freezeall");
                    }
                }else {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
            }

        }


        return false;
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.frozenPlayers.contains(player.getUniqueId())){

            player.teleport(player.getLocation());
        }

    }
}
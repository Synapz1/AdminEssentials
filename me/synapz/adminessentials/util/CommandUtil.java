package me.synapz.adminessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandUtil {

    // advanced permission check
    public boolean permissionCheck(CommandSender sender, String permission) {
        // check if the sender has permissions or if they are console
        boolean hasPermissions = (sender.hasPermission(permission) || permission.equals("console")) ? true : false;

        if (hasPermissions) {
            return true;
        } else {
            sender.sendMessage(CommandMessenger.NO_PERMS);
            return false;
        }
    }

    // advanced null player check
    public boolean isPlayerOnline(CommandSender sender, Player playerToCheck, String playerToCheckName) {
        if (playerToCheck == null) {
            sender.sendMessage(CommandMessenger.nullPlayerException(playerToCheckName));
            return false;
        } else {
            return true;
        }
    }

    public void setGamemode(Player target, String targetString, CommandSender sender, GameMode gamemode, String permission) {
        if (permissionCheck(sender, permission)) {
            if (isPlayerOnline(sender, target, targetString)) {
                CommandMessenger.gamemodeChangeMessenger(target, sender, gamemode);
            }
        }

    }

    public void teleport(CommandSender sender, Player target, String targetString, Player target2, String target2String, String permission) {

        Player player = (Player) sender;

        if (permissionCheck(sender, permission)) {
            if (isPlayerOnline(sender, target, targetString)) {
                if (isPlayerOnline(sender, target2, target2String)) {

                    CommandMessenger.tpMessenger(player, target, target2);
                }
            }

        }

    }

    public void tppos(String x, String y, String z, Player player, String permission) {
        if (player.hasPermission(permission)) {
            try {
                //Convert arguments to integers.
                int xloc = Integer.parseInt(x);
                int yloc = Integer.parseInt(y);
                int zloc = Integer.parseInt(z);
                Location cords = new Location(player.getWorld(), xloc, yloc, zloc);
                player.teleport(cords);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Make sure your coordinance are intergers!");
                player.sendMessage(ChatColor.RED + "Usage: /tppos <x> <y> <z>");
            }
        } else {
            player.sendMessage(CommandMessenger.NO_PERMS);
        }
    }

    public void tpall(Player player, String permission) {
        if (permissionCheck(player, permission)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                // check to make sure the player won't be teleported to themselves
                if (!p.equals(player)) {
                    p.teleport(player.getLocation());
                    CommandMessenger.tpMessenger(player, p, player);
                }
            }
            player.sendMessage(ChatColor.GOLD + "All players have been teleported to you!");
        }

    }

    public void vanish(CommandSender sender, Player target, String targetString, String permission) {
        if (permissionCheck(sender, permission)) {
            if (isPlayerOnline(sender, target, targetString)) {
                CommandMessenger.vanishMessenger(sender, target);
            }
        }
    }
}
package me.synapz.adminessentials.util;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

<<<<<<< HEAD
<<<<<<< HEAD
public class Utils {

=======
>>>>>>> origin/master
=======
>>>>>>> 6597925aa39d00d3ab28cb0d33b4189fb2012e07
    private Utils() {}

    public static ArrayList<String> allPermArguments(String permission) {
        ArrayList<String> permissions = new ArrayList<>();
        for (int i = 1; i < 200; i++) {
            permissions.add(permission + " " + i);
        }
        return permissions;
    }

    public static ArrayList<Integer> allArguments() {
        ArrayList<Integer> args = new ArrayList<>();
        for (int i = 1; i < 200; i++) {
            args.add(i);
        }
        return args;
    }


    public static boolean isPlayerOnline(CommandSender sender, String name) {
        Player player = Bukkit.getPlayer(name);
        if (player != null) {
            return true;
        }
        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + name + "'" + ChatColor.GOLD + " wasn't found.");
        return false;
    }

    public static void setGamemode(CommandSender sender, Player player, GameMode gamemode) {
        if (sender.getName().equals(player.getName())) {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + "yourself" + ChatColor.GOLD + " to " + gamemode + " mode!");
        } else {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + player.getName() + ChatColor.GOLD + " to " + gamemode + " mode!");
            player.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " set you to " + gamemode + " mode!");
        }
        player.setGameMode(gamemode);
    }

}

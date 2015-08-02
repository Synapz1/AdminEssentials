package me.synapz.adminessentials.util;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.ChatColor.GOLD;


public class Utils {

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

    public static void mute(CommandSender sender, Player target, boolean isPlayerOnline, boolean muteAll) {
        Config config = Config.getInstance();
        if (!isPlayerOnline) {
            return;
        }
        if (config.isMuted(target)) {
            config.setMute(sender, target, false, muteAll);
        } else {
            config.setMute(sender, target, true, muteAll);
        }
    }

    public static ArrayList<Integer> makeArgs(int...args) {
        ArrayList<Integer> arguments = new ArrayList<Integer>();
        for (Integer arg : args) {
            arguments.add(arg);
        }
        return arguments;
    }

    public static String messagerBuilder(String[] args) {
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            // if i-1 == args, its the last run so we need to remove the " " at the end
            msg = i+1 == args.length ? msg + args[i] : msg + args[i] + " ";
        }
        return msg;
    }

    public static void teleport(Player sender, Player target) {
        sender.sendMessage(GOLD + "Teleporting...");
        sender.teleport(target.getLocation());
    }

}

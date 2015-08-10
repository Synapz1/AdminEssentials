package me.synapz.adminessentials.util;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.ChatColor.*;


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
        sender.sendMessage(GOLD + "Player " + RED + "'" + name + "'" + GOLD + " wasn't found.");
        return false;
    }

    public static void setGamemode(CommandSender sender, Player player, GameMode gamemode) {
        Utils.sendSenderMessage(sender, player, GOLD + "You set " + RED + player.getName() + GOLD + " to " + RED + gamemode + GOLD + " mode!");
        if (sender.getName().equals(player.getName())) {
            sender.sendMessage(GOLD + "You set " + RED + "yourself" + GOLD + " to " + RED + gamemode + GOLD + " mode!");
        } else {
            player.sendMessage(GOLD + "Player " + RED + sender.getName() + GOLD + " set you to " + RED + gamemode + GOLD + " mode!");
        }
        player.setGameMode(gamemode);
    }

    public static void mute(CommandSender sender, Player target, boolean isPlayerOnline, boolean muteAll) {
        Config config = Config.getInstance();
        if (!isPlayerOnline) {
            return;
        }
        if (config.isMuted(target)) {
            config.setMute(sender, target, false);
        } else {
            config.setMute(sender, target, true);
        }
    }

    public static ArrayList<Integer> makeArgs(int...args) {
        ArrayList<Integer> arguments = new ArrayList<>();
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

    public static void sendSenderMessage(CommandSender sender, Player target, String message) {
        if (!sender.getName().equals(target.getName())) {
            sender.sendMessage(message);
        }
    }


}

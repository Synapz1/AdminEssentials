package me.synapz.adminessentials.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandMessenger {

    private static ArrayList<String> invisiblePlayers = new ArrayList<String>();
    public static final String NO_PERMS = ChatColor.DARK_RED + "You don't have access to that command!";
    public static final String NO_CONSOLE_PERMS = ChatColor.DARK_RED + "Console does not have access to that command!";
    public static final String PLAYER_NOT_BANNED = ChatColor.RED + "Player is not banned!";

    public static String nullPlayerException(String arg) {
        return ChatColor.GOLD + "Player " + ChatColor.RED + "'" + arg.toString() + "'" + ChatColor.GOLD + " wasn't found.";
    }

    private static void vanish(Player player) {
        if (invisiblePlayers.contains(player.getName())) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(player);
            }
            invisiblePlayers.remove(player.getName());
            player.sendMessage(ChatColor.GOLD + "Vanish: " + ChatColor.RED + "OFF");
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(player);
            }
            invisiblePlayers.add(player.getName());
            player.sendMessage(ChatColor.GOLD + "Vanish: " + ChatColor.RED + "ON");

        }
    }


    protected static void gamemodeChangeMessenger(Player target, CommandSender sender, GameMode gm) {
        // if we don't have a target player to switch but instead we want to switch their own game mode
        // set the target = to the sender and their own game mode will change

        if (target.equals(sender)) {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.setGameMode(gm);
        } else {
            target.setGameMode(gm);
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " set you to " + gm + " mode!");
        }
    }

    protected static void tpMessenger(Player sender, Player target, Player target1) {
        // sender -> target /tp
        if (target.equals(target1)) {
            sender.teleport(target.getLocation());
            sender.sendMessage(ChatColor.GOLD + "Teleporting...");
        }
        // target -> sender /tphere
        else {
            target.teleport(target1.getLocation());
            target.sendMessage(ChatColor.GOLD + "Teleporting...");
        }
    }

    protected static void vanishMessenger(CommandSender sender, Player target) {
        String type = !invisiblePlayers.contains(target.getName()) ? "ON" : "OFF";
        vanish(target);

        if (!target.equals(sender)) {
            sender.sendMessage(ChatColor.GOLD + "Vanish (" + target.getName() + "): " + ChatColor.RED + type);
        }
    }

    public void wrongUsage(CommandSender sender, int i, String commandUsage) {
        // 0 means to little arguments, 1 means to many arguments

        if (i == 0) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        } else if (i == 1) {
            sender.sendMessage(ChatColor.RED + "To many arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        }
    }

    protected static void onMute(CommandSender sender, Player target, boolean toMute) {
        String type = toMute ? "muted!" : "unmuted!";

        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + target.getName() + ChatColor.GOLD + " was " + type);
        target.sendMessage(ChatColor.GOLD + "You have been " + type);
    }

    protected static void onFreeze(CommandSender sender, Player target, boolean toFreeze) {
        String type = toFreeze ? "frozen!" : "unfrozen!";

        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + target.getName() + ChatColor.GOLD + " was " + type);
        target.sendMessage(ChatColor.GOLD + "You have been " + type);
    }

    protected static void onBan(CommandSender sender, String target, String reason, boolean toBan) {
        ChatColor r = ChatColor.RED;
        ChatColor g = ChatColor.GOLD;
        if (toBan) {
            // banning...
            Bukkit.broadcastMessage(g + "Player " + r + sender.getName() + g + " banned " + r + target + g + " for " + reason);
        } else {
            // unbanning...
            Bukkit.broadcastMessage(g + "Player " + r + sender.getName() + g + " unbanned " + r + target + g + "!");
        }
    }
}
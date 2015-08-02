package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandBurn extends AdminEssentialsCommand implements ConsoleCommand {

    public void burn(CommandSender sender, Player player, int ticks) {
        player.setFireTicks(ticks);
        player.sendMessage(ChatColor.GOLD + "You were set on fire for " + ChatColor.RED + (ticks / 20) + ChatColor.GOLD + " seconds!");
        sender.sendMessage(ChatColor.GOLD + "Set " + sender + " on fire for " + ChatColor.RED + (ticks / 20) + ChatColor.GOLD + " seconds!");
    }

    public void onCommand(Player player, String[] args) {
        int dur = 300;
        Player target = args.length == 0 ? player : Bukkit.getServer().getPlayer(args[0]);

        if (args.length != 0 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }

        // dont need to correctly run args.length == 1 because the default is 300 ticks.
        // dont need to correctly run args.length == 0 because the target was auto set to themself
        if (args.length == 2) {
            try {
                dur = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Enter a valid integer!");
                return;
            }
        }
        burn(player, target, dur);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        int dur = 300;
        Player target = Bukkit.getServer().getPlayer(args[0]);

        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        // dont need to correctly run args.length == 1 because the default is 300 ticks.
        if (args.length == 2) {
            try {
                dur = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Enter a valid integer!");
                return;
            }
        }
        burn(sender, target, dur);
    }

    public String getName() {
        return "burn";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.burn 0");
        permissions.add("adminessentials.burn.others 1");
        permissions.add("adminessentials.burn.others 2");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0, 1, 2);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1, 2);
    }

    public String[] getArguments() {
        return new String[] {"<player> [seconds]"};
    }
}
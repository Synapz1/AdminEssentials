package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandBurn extends AdminEssentialsCommand implements ConsoleCommand {

    public void burn(CommandSender sender, Player player, int seconds) {
        player.setFireTicks(seconds * 20);
        player.sendMessage(RED + "You " + GOLD + "were set on fire for " + RED + seconds + GOLD + " seconds!");
        Utils.sendSenderMessage(sender, player, GOLD + "Set " + RED + player.getName() + GOLD + " on fire for " + RED + seconds + GOLD + " seconds!");
    }

    public void onCommand(Player player, String[] args) {
        int dur = 15;
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
                player.sendMessage(RED + "Enter a valid integer!");
                player.sendMessage(RED + this.getCorrectUsage());
                return;
            }
        }
        burn(player, target, dur);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        int dur = 15;
        Player target = Bukkit.getServer().getPlayer(args[0]);

        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        // dont need to correctly run args.length == 1 because the default is 300 ticks.
        if (args.length == 2) {
            try {
                dur = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(RED + "Enter a valid integer!");
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
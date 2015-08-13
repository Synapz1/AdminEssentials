package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.objects.Jail;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandJail extends AdminEssentialsCommand implements ConsoleCommand {

    // todo: set output and better checks

    public void onCommand(Player player, String[] args) {
        // jail player
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        Jail jail = Jail.getJail(args[1]);

        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }

        if (args.length == 2) {
            jail.jail(target);
        } else {
            Jail.TimeType type = null;
            int time;
            try {
                time = Integer.parseInt(args[2]);
            }catch (NumberFormatException ex) {
                // input valid interger
                return;
            }
            if (args[4].equalsIgnoreCase("sec")) type = Jail.TimeType.SECONDS;
            if (args[4].equalsIgnoreCase("min")) type = Jail.TimeType.MINUTES;
            if (args[4].equalsIgnoreCase("day")) type = Jail.TimeType.DAYS;

            if (type != null) {
                jail.jail(target, time, type);
            } else {
                // send message
            }
        }
        // send message
    }

    public String getName() {
        return "jail";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.jail 2");
        permissions.add("adminessentials.jail 4");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(2, 4);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(2, 4);
    }

    public String[] getArguments() {
        return new String[] {"<player> <jail> [time] [sec/min/day]"};
    }

}
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
        Jail jail = Jail.getJail(args[1]);

        if (Utils.isPlayerOnline(sender, args[0]) && !Jail.isJailNull(sender, args[1])) {
            jail.jail(Bukkit.getPlayer(args[0]));
        }
    }

    public String getName() {
        return "jail";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.jail 1");
        permissions.add("adminessentials.jail 2");
        permissions.add("adminessentials.jail 3");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1, 2, 3);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1, 2, 3);
    }

    public String[] getArguments() {
        return new String[] {"<player> [time] [sec/min/day]"};
    }

}
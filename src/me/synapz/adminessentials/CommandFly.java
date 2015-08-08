package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandFly extends AdminEssentialsCommand implements ConsoleCommand {

    private void fly(CommandSender sender, Player target) {
        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage(GOLD + "Fly mode was " + RED + "disabled");
            Utils.sendSenderMessage(sender, target, GOLD + "Fly mode was" + RED + " disabled" + GOLD + " for " + RED + target.getName());
        } else {
            target.setAllowFlight(true);
            target.sendMessage(GOLD + "Fly mode was " + RED + "enabled");
            Utils.sendSenderMessage(sender, target, GOLD + "Fly mode was " + RED + "enabled" + GOLD + " for " + RED + target.getName());
        }
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : Bukkit.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        fly(player, target);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        fly(sender, target);
    }

    public String getName() {
        return "fly";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.fly 0");
        permissions.add("adminessentials.fly.others 1");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0, 1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }
}
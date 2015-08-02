package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandVanish extends AdminEssentialsCommand implements ConsoleCommand {

    private static ArrayList<String> invisiblePlayers = new ArrayList<String>();

    private void vanish(CommandSender sender, Player target) {
        String action;
        if (invisiblePlayers.contains(target.getName())) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(target);
            }
            target.sendMessage(GOLD + "Vanish disabled.");
            action = " disabled";
            invisiblePlayers.remove(target.getName());
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(target);
            }
            target.sendMessage(GOLD + "Vanish enabled.");
            action = " enabled";
            invisiblePlayers.add(target.getName());
        }
        if (!sender.getName().equals(target.getName())) {
            sender.sendMessage(GOLD + "You " + action + " invisibility for " + RED + target.getName());
        }
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : Bukkit.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        vanish(player, target);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        vanish(sender, target);
    }

    public String getName() {
        return "v";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.vanish 0");
        permissions.add("adminessentials.vanish.others 1");
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

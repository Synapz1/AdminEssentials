package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import static org.bukkit.ChatColor.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandKillall extends AdminEssentialsCommand implements ConsoleCommand {

    private void killall(CommandSender sender) {
        int killed = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(sender.getName())) {
                p.sendMessage(GOLD + "You died.");
                p.setHealth(0);
                killed++;
            }
        }
        sender.sendMessage(GOLD + "Killed " + RED + killed + GOLD + " players");
    }

    public void onCommand(Player player, String[] args) {
        killall(player);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        killall(sender);
    }

    public String getName() {
        return "killall";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.killall 0");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(0);
    }

    public String[] getArguments() {
        return new String[] {""};
    }

}

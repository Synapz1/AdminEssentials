package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandKickall extends AdminEssentialsCommand implements ConsoleCommand {

    private void kickall(CommandSender sender) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(sender.getName())) {
                p.kickPlayer("Kicked from the server!");
            }
        }
        sender.sendMessage(GOLD + "All players kicked.");
    }

    public void onCommand(Player player, String[] args) {
        kickall(player);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        kickall(sender);
    }

    public String getName() {
        return "kickall";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.kickall 0");
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

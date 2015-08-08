package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandKill extends AdminEssentialsCommand implements ConsoleCommand {

    private void kill(CommandSender sender, Player target, boolean isPlayerOnline) {
        String message;
        if (!isPlayerOnline) {
            return;
        }

        if (sender.getName().equals(target.getName())) {
            message = GOLD + "You committed suicide.";
        } else {
            message = GOLD + "You died!";
            sender.sendMessage(GOLD + "You killed " + RED + target.getName());
        }
        target.sendMessage(message);
        target.setHealth(0);
    }

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        kill(sender, target, Utils.isPlayerOnline(sender, args[0]));
    }

    public String getName() {
        return "kill";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.kill 1");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }
}
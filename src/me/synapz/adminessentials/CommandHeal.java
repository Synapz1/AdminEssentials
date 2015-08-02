package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Utils;
import static org.bukkit.ChatColor.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHeal extends AdminEssentialsCommand implements ConsoleCommand {

    private void heal(CommandSender sender, Player target) {
        if (!sender.getName().equals(target.getName())) {
            sender.sendMessage(GOLD + "You healed " + RED + target.getName());
        } else {
            sender.sendMessage(GOLD + "Healed.");
        }
        target.setHealth(20);
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : player.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        heal(player, target);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        if (Utils.isPlayerOnline(sender, args[0])) {
            heal(sender, target);
        }
    }

    public String getName() {
        return "heal";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.heal 0");
        permissions.add("adminessentials.heal.others 1");
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
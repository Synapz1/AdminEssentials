package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandCi extends AdminEssentialsCommand implements ConsoleCommand {

    // TODO: someone remove all this duplicated code... maybe a method with boolean (isConsole)
    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : player.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        target.getInventory().clear();
        player.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.RED + target.getName() + ChatColor.GOLD + " inventory!");
        target.sendMessage(ChatColor.GOLD + "Inventory cleared!");
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        target.getInventory().clear();
        sender.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.RED + target.getName() + ChatColor.GOLD + " inventory!");
        target.sendMessage(ChatColor.GOLD + "Inventory cleared!");
    }

    public String getName() {
        return "ci";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.ci 0");
        permissions.add("adminessentials.ci.others 1");
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
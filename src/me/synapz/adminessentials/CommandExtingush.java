package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandExtingush extends AdminEssentialsCommand implements ConsoleCommand {

    public void ext(CommandSender sender, Player player) {
        player.setFireTicks(0);
        player.sendMessage(ChatColor.GOLD + "You were extinguished!");

        if (!sender.getName().equals(player.getName())) {
            sender.sendMessage(ChatColor.GOLD + "You extinguished fire from " + ChatColor.RED + player.getName());
        }
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : player.getServer().getPlayer(args[0]);
        if (args.length == 1 && Utils.isPlayerOnline(player, target.getName())) {
            ext(player, target);
        }
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        if (Utils.isPlayerOnline(sender, target.getName())) {
            ext(sender, target);
        }
    }

    public String getName() {
        return "ext";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.ext 0");
        permissions.add("adminessentials.ext.others 1");
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

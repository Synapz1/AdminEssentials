package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.RED;

public class CommandKick extends AdminEssentialsCommand implements ConsoleCommand {

    private void kick(CommandSender sender, Player target, String[] args) {
        String message = args.length == 1 ? "Kicked from server." : Utils.messagerBuilder(args);
        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        target.kickPlayer(message);
        Bukkit.broadcastMessage(GOLD + "Player " + RED + sender.getName() + GOLD + " kicked " + RED + target.getName() + GOLD + " for " + RED + message);
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : player.getServer().getPlayer(args[0]);
        kick(player, target, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        kick(sender, target, args);
    }

    public String getName() {
        return "kick";
    }

    public ArrayList<String> getPermissions() {
        return Utils.allPermArguments("adminessentials.kick");
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.allArguments();
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.allArguments();
    }

    public String[] getArguments() {
        return new String[] {"<player> [reason]"};
    }
}
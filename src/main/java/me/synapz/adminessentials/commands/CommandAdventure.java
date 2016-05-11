package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdventure extends AdminEssentialsCommand implements ConsoleCommand {

    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            Utils.setGamemode(player, player, GameMode.ADVENTURE);
        } else if (args.length == 1) {
            Player targetPlayer = player.getServer().getPlayer(args[0]);

            if (Utils.isPlayerOnline(player, args[0])) {
                Utils.setGamemode(player, targetPlayer, GameMode.ADVENTURE);
            }
        }
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player targetPlayer = sender.getServer().getPlayer(args[0]);

        if (Utils.isPlayerOnline(sender, args[0])) {
            Utils.setGamemode(sender, targetPlayer, GameMode.ADVENTURE);
        }
    }

    public String getName() {
        return "gma";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.adventure 0");
        permissions.add("adminessentials.adventure.others 1");
        return permissions;
    }
    
    public String[] getArguments() {
        return new String[] {"<player>"};
    }

    public ArrayList<Integer> handledArgs() {
    	return Utils.makeArgs(0, 1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }
}
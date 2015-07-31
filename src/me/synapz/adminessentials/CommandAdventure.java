package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
        ArrayList<Integer> args = new ArrayList<>();
        args.add(0);
        args.add(1);
    	return args;
    }

    public ArrayList<Integer> consoleHandledArgs() {
        ArrayList<Integer> args = handledArgs();
        args.remove(0);
        return args;
    }
}
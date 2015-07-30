package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Utils;

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

    public HashMap<Integer, String> getPermissions() {
    	HashMap<Integer, String> permissions = new HashMap<>();
    	permissions.values().add("adminessentials.adventure");
    	permissions.values().add("adminessentials.adventure.others");
    	permissions.keySet().add(0);
    	permissions.keySet().add(1);
        return permissions;
    }
    
    public String[] getArguments() {
        return new String[] {"<player>"};
    }

    public int[] handledArgs() {
    	return new int[] {0, 1};
    }
    
    public int[] consoleHandledArgs() {
    	return new int[] {0};
    }
}
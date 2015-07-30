package me.synapz.adminessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandAnnounce extends AdminEssentialsCommand implements ConsoleCommand {

    private String messageBuilder(String[] args) {
        String msg1 = " ";
        for (int i = 0; i < args.length; i++) {
            msg1 = msg1 + args[i] + " ";
        }
        return msg1;
    }

    public void onCommand(Player player, String[] args) {
    	onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', messageBuilder(args)));
    }

    public String getName() {
        return "announce";
    }

    public HashMap<Integer, String> getPermissions() {
        // todo, find a better way to do this instead of adding 1OO items to hashmap
        HashMap<Integer, String> permission = new HashMap<>();
        for (int i = 1; i < 200; i++) {
        	permission.keySet().add(i);
        	permission.values().add("adminessentials.announce");
        }
        return permission;
    }

    public String[] getArguments() {
        return new String[] {"<message>"};
    }

    // TODO: there has to be a better way to do this other than creating a 200 int array...
    public int[] handledArgs() {
    	int[] args = new int[200];
    	for (int i = 1; i < 200; i++) {
    		args[i] = i;
    	}
    	return args;
    }
    
    public int[] consoleHandledArgs() {
    	return handledArgs();
    }
    
}

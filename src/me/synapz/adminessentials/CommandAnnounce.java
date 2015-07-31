package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', messageBuilder(args)));
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', messageBuilder(args)));
    }

    public String getName() {
        return "announce";
    }

    public ArrayList<String> getPermissions() {
        return Utils.allPermArguments("adminessentials.announce");
    }

    public String[] getArguments() {
        return new String[] {"<message>"};
    }

    // TODO: there has to be a better way to do this other than creating a 200 int array...
    public ArrayList<Integer> handledArgs() {
    	return Utils.allArguments();
    }
    
    public ArrayList<Integer> consoleHandledArgs() {
    	return Utils.allArguments();
    }
}

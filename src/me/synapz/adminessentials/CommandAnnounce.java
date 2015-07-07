package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAnnounce implements CommandExecutor {
    private static final String ANNOUNCE_SUFFIX = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]";

    private String messageBuilder(String[] args) {
        String msg1 = " ";
        for (int i = 0; i < args.length; i++) {
            msg1 = msg1 + args[i] + " ";
        }

        return msg1;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        CommandUtil utils = new CommandUtil();
        CommandMessenger messenger = new CommandMessenger();

        // permission checks
        if (sender instanceof Player) {
            if (!utils.permissionCheck(sender, "adminessentials.announce")) {
                return true;
            }
        }

        if (args.length == 0) {
            messenger.wrongUsage(sender, 0, "/announce <message>");

        } else if (args.length >= 1) {

            String message = messageBuilder(args);
            Bukkit.broadcastMessage(ANNOUNCE_SUFFIX + ChatColor.RESET + message);

        }

        return false;
    }
}

package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMarco
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        CommandUtil utils = new CommandUtil();
        CommandMessenger messenger = new CommandMessenger();

        // if the sender is a player, check their permissions
        if (sender instanceof Player)
        {
            if(!utils.permissionCheck(sender, "adminessentials.marco"))
            {
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("marco")) {
            if (args.length == 0)
            {
                sender.sendMessage("Polo!");
            }
            else if (args.length >= 1)
            {
                messenger.wrongUsage(sender, 1, "/marco");
            }
        }
        return false;
    }
}

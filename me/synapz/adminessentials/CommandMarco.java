package me.synapz.adminessentials;

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
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("marco"))) {
            if (args.length == 0) {
                sender.sendMessage("Polo!");
            } else if (args.length >= 1) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /marco");
            }
        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("marco")) {
                if (player.hasPermission("adminessentials.marco")) {
                    if (args.length == 0) {
                        player.sendMessage("Polo!");
                    } else if (args.length >= 1) {
                        player.sendMessage(ChatColor.RED + "To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /marco");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED +
                            "You don't have access to that command!");
                }
            }
        }

        return false;
    }
}
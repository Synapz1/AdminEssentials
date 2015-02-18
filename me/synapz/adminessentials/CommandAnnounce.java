package me.synapz.adminessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAnnounce
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("announce"))) {
            if (args.length == 0)
            {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /announce <message>");
            }
            if (args.length >= 1)
            {
                String msg1 = " ";
                for (int i = 0; i < args.length; i++)
                {
                    msg1 = msg1 + args[i] + " ";
                }
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + msg1);
            }
        }

        if ((sender instanceof Player))
        {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("announce"))
            {
                if (player.hasPermission("adminessentials.announce"))
                {
                    if (args.length == 0)
                    {
                        player.sendMessage(ChatColor.RED + "Not enough arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /announce <message>");
                    }
                    if (args.length >= 1)
                    {
                        String msg1 = " ";
                        for (int i = 0; i < args.length; i++)
                        {
                            msg1 = msg1 + args[i] + " ";
                        }
                        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + msg1);
                    }
                } else
                {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");
                }
            }
        }

        return false;
    }
}
package me.synapz.adminessentials.util;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reference {

    protected static final String NO_PERMS = ChatColor.DARK_RED + "You don't have access to that command!";

    protected static String nullPlayerException(Player p)
    {
        return ChatColor.GOLD + "Player " + ChatColor.RED + "'" + p + "'" + ChatColor.GOLD + " wasn't found.";
    }


    protected static void changeGamemodeMessage(Player target, Player sender, GameMode gm)
    {
        if(target.equals(sender))
        {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            sender.setGameMode(gm);
        }
        else
        {
            target.setGameMode(gm);
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " set you to " + gm + " mode!");
        }
    }


    public void wrongUsage(CommandSender p, int args, String commandUsage)
    {
        Player sender = (Player) p;

        if(args == 0)
        {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        }
        else if(args >= 2)
        {
            sender.sendMessage(ChatColor.RED + "To many arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        }
    }
}

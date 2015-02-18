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
        // if we don't have a target player to switch but instead we want to switch their own game mode
        // set the target = to the sender and their own game mode will change

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


    public void wrongUsage(CommandSender p, int i, String commandUsage)
    {
        // 0 means to little arguments, 1 means to many arguments
        Player sender = (Player) p;

        if(i == 0)
        {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        }
        else if(i == 1)
        {
            sender.sendMessage(ChatColor.RED + "To many arguments!");
            sender.sendMessage(ChatColor.RED + "Usage: " + commandUsage);
        }
    }

}

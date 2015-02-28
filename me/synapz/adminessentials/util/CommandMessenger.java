package me.synapz.adminessentials.util;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMessenger  {

    public static final String NO_PERMS = ChatColor.DARK_RED + "You don't have access to that command!";
    public static final String NO_CONSOLE_PERMS = ChatColor.DARK_RED + "Console does not have access to that command!";

    public static String nullPlayerException(String arg)
    {
        return ChatColor.GOLD + "Player " + ChatColor.RED + "'" + arg.toString() + "'" + ChatColor.GOLD + " wasn't found.";
    }


    protected static void gamemodeChangeMessenger(Player target, CommandSender sender, GameMode gm)
    {
        // if we don't have a target player to switch but instead we want to switch their own game mode
        // set the target = to the sender and their own game mode will change

        if(target.equals(sender))
        {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.setGameMode(gm);
        }
        else
        {
            target.setGameMode(gm);
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " set you to " + gm + " mode!");
        }
    }

    protected static void tpMessenger(Player sender, Player target, Player target1)
    {
        // sender -> target /tp
        if (target.equals(target1))
        {
            sender.teleport(target.getLocation());
            sender.sendMessage(ChatColor.GOLD + "Teleporting...");
        }
        // target -> sender /tphere
        else
        {
            target.teleport(target1.getLocation());
            target.sendMessage(ChatColor.GOLD + "Teleporting...");
        }
    }

    public void wrongUsage(CommandSender sender, int i, String commandUsage)
    {
        // 0 means to little arguments, 1 means to many arguments

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

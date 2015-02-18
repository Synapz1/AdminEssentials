package me.synapz.adminessentials.util;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtil {

    public void setGamemode(Player target, CommandSender p, GameMode gamemode, String permission)
    {
        // in case the sender is a player, we convert it from a CommandSender to a Player
        Player sender = (Player) p;

        if (sender.hasPermission(permission) || permission.equals("console"))
        {
            if (target == null)
            {
                sender.sendMessage(Reference.nullPlayerException(target));
            }
            else
            {
                Reference.changeGamemodeMessage(target, sender, gamemode);
            }
        }
        else
        {
            sender.sendMessage(Reference.NO_PERMS);
        }

    }
}
package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.CommandMessenger;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdventure
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        CommandMessenger commandMessenger = new CommandMessenger();
        CommandUtil commands = new CommandUtil();

        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gma")))
        {
            if (args.length == 0)
            {
                commandMessenger.wrongUsage(sender, 0, "/gma <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, args[0], sender, GameMode.ADVENTURE, "console");
            }
            else if (args.length >= 2)
            {
                commandMessenger.wrongUsage(sender, 1, "/gma <player>");
            }

        }

        if (((sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gma")))
        {
            Player player = (Player)sender;


            if (args.length == 0)
            {
                commands.setGamemode(player, null, player,  GameMode.ADVENTURE, "adminessentials.adventure");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, args[0], player, GameMode.ADVENTURE, "adminessentials.adventure.others");
            }
            else if (args.length >= 2)
            {
                commandMessenger.wrongUsage(player, 1, "/gma <player>");
            }

        }

        return false;
    }
}
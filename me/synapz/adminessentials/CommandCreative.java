package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.CommandMessenger;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreative
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        CommandMessenger reference = new CommandMessenger();
        CommandUtil commands = new CommandUtil();

        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmc")))
        {
            if (args.length == 0)
            {
                reference.wrongUsage(sender, 0, "/gmc <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, args[0], sender, GameMode.CREATIVE, "console");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(sender, 1, "/gmc <player>");
            }

        }

        if (((sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmc")))
        {
            Player player = (Player)sender;

            if (args.length == 0)
            {
                commands.setGamemode(player, null, player, GameMode.CREATIVE, "adminessentials.creative");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, args[0], player, GameMode.CREATIVE, "adminessentials.creative.others");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(player, 1, "/gmc <player>");
            }

        }

        return false;
    }
}
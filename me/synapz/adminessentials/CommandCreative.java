package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Reference;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreative
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Reference reference = new Reference();
        CommandUtil commands = new CommandUtil();

        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmc"))) {
            if (args.length == 0)
            {
                reference.wrongUsage(sender, args.length, "/gmc <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, sender, GameMode.CREATIVE, "console");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(sender, args.length, "/gmc <player>");
            }

        }

        if (((sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmc")))
        {
            Player player = (Player)sender;

            if (args.length == 0)
            {
                reference.wrongUsage(player, args.length, "/gmc <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, player, GameMode.CREATIVE, "console");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(player, args.length, "/gmc <player>");
            }

        }

        return false;
    }
}
package me.synapz.adminessentials;


import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Reference;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpectator implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Reference reference = new Reference();
        CommandUtil commands = new CommandUtil();

        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmss")))
        {
            if (args.length == 0)
            {
                reference.wrongUsage(sender, 0, "/gmss <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, sender, GameMode.SPECTATOR, "console");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(sender, 1, "/gmss <player>");
            }

        }

        if (((sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("gmss")))
        {
            Player player = (Player)sender;

            if (args.length == 0)
            {
                commands.setGamemode(player, player, GameMode.SPECTATOR, "adminessentials.spectator");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                commands.setGamemode(targetPlayer, player, GameMode.SPECTATOR, "adminessentials.spectator.others");
            }
            else if (args.length >= 2)
            {
                reference.wrongUsage(player, 1, "/gmss <player>");
            }

        }

        return false;
    }
}

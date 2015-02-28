package me.synapz.adminessentials;


import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        CommandUtil commands = new CommandUtil();
        CommandMessenger messenger = new CommandMessenger();

        if ((!(sender instanceof Player)) && (cmd.getName().equalsIgnoreCase("v")))
        {
            if (args.length == 0)
            {
                messenger.wrongUsage(sender, 0, "/v <player>");
            }
            else if (args.length == 1)
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                commands.vanish(sender, target, args[0], "console");
            }
            else if(args.length >= 2)
            {
                messenger.wrongUsage(sender, 1, "/v <player>");
            }
        }

        if ((sender instanceof Player) && (cmd.getName().equalsIgnoreCase("v")))
        {
            Player player = (Player)sender;

            if(args.length == 0)
            {
                commands.vanish(sender, player, player.getName(), "adminessentials.vanish");
            }
            else if(args.length == 1)
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                commands.vanish(sender, target, args[0].toString(), "adminessentials.vanish.others");
            }
            else if (args.length >= 2)
            {
                messenger.wrongUsage(sender, 1, "/v <player>");
            }
        }

        return false;
    }
}

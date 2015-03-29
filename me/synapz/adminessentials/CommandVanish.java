package me.synapz.adminessentials;


import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        CommandUtil commands = new CommandUtil();
        CommandMessenger messenger = new CommandMessenger();


        if ((sender instanceof CommandSender) && (cmd.getName().equalsIgnoreCase("v"))) {
            boolean console = (!(sender instanceof Player)) ? true : false;

            if (args.length == 0) {
                // a separate method for consoles
                if (console) {
                    messenger.wrongUsage(sender, 0, "/v <player>");
                    return true;
                }
                Player player = (Player) sender;
                commands.vanish(sender, player, sender.getName(), "adminessentials.vanish");

            } else if (args.length == 1) {

                Player target = Bukkit.getServer().getPlayer(args[0]);
                commands.vanish(sender, target, args[0].toString(), "adminessentials.vanish.others");

            } else if (args.length >= 2) {

                messenger.wrongUsage(sender, 1, "/v <player>");

            }
        }
        return false;
    }
}

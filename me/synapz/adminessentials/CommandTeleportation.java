package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.CommandMessenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeleportation
        implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        CommandMessenger commandMessenger = new CommandMessenger();
        CommandUtil commands = new CommandUtil();

        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("tp")) {
                // /tp || /tp arg1
                if (args.length == 0 || args.length == 1) {
                    commandMessenger.wrongUsage(sender, 0, "/tp <player> <player>");
                }
                // /tp arg1 arg2
                else if (args.length == 2) {

                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    Player targetPlayer1 = sender.getServer().getPlayer(args[1]);

                    commands.teleport(sender, targetPlayer, args[0].toString(), targetPlayer1, args[1].toString(), "console");
                } else if (args.length >= 2) {

                    commandMessenger.wrongUsage(sender, 1, "/tp <player> <player>");

                }
            }
            else if (args.length >= 0 && cmd.getName().equalsIgnoreCase("tphere") || cmd.getName().equalsIgnoreCase("tppos") || cmd.getName().equalsIgnoreCase("tpall")) {
                sender.sendMessage(CommandMessenger.NO_CONSOLE_PERMS);
            }
        }

        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("tp")) {
                if (args.length == 0) {
                    commandMessenger.wrongUsage(player, 0, "/tp <player>");
                }
                // /tp <player>
                else if (args.length == 1) {

                    Player targetPlayer = player.getServer().getPlayer(args[0]);

                    commands.teleport(player, targetPlayer, args[0].toString(), targetPlayer, args[0].toString(), "adminessentials.tp");
                }
                // /tp <player> <player>
                else if (args.length == 2) {

                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    Player targetPlayer1 = player.getServer().getPlayer(args[1]);

                    commands.teleport(player, targetPlayer, args[0].toString(), targetPlayer1, args[1].toString(), "adminessentials.tp.others");

                } else if (args.length >= 3) {
                    commandMessenger.wrongUsage(player, 1, "/tp <player> [player]");
                }
            }

            else if (cmd.getName().equalsIgnoreCase("tphere")) {

                if (args.length == 0) {
                    commandMessenger.wrongUsage(player, 0, "/tphere <player>");
                } else if (args.length == 1) {

                    Player targetPlayer = player.getServer().getPlayer(args[0]);

                    commands.teleport(player, targetPlayer, args[0].toString(), player, player.getName(), "adminessentials.tphere");
                } else if (args.length >= 2) {

                    commandMessenger.wrongUsage(player, 1, "/tphere <player>");
                }

            } else if (cmd.getName().equalsIgnoreCase("tppos")) {
                if (args.length == 0 || args.length == 1 || args.length == 2) {
                    commandMessenger.wrongUsage(player, 0, "/tppos <x> <y> <z>");
                } else if (args.length == 3) {
                    commands.tppos(args[0], args[1], args[2], player, "adminessentials.tppos");
                } else if (args.length >= 4) {
                    player.sendMessage(ChatColor.RED + "To many arguments!");
                    player.sendMessage(ChatColor.RED + "Usage: /tppos <x> <y> <z>");
                }

            } else if ((cmd.getName().equalsIgnoreCase("tpall"))) {

                if (args.length == 0) {
                    commands.tpall(player, "adminessentials.tpall");
                } else if (args.length >= 1) {
                    commandMessenger.wrongUsage(player, 1, "/tpall");
                }

            }
        }
        return false;
    }
}
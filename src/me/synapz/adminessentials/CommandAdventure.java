package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.CommandMessenger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdventure extends AdminEssentialsCommand implements ConsoleCommand {

    CommandMessenger commandMessenger = new CommandMessenger();
    CommandUtil commands = new CommandUtil();

    public void onCommand(Player player, String[] args) {

        /*
        if (args.length == 0) {

            commands.setGamemode(player, null, player, GameMode.ADVENTURE, "adminessentials.adventure");

        } else if (args.length == 1) {

            Player targetPlayer = player.getServer().getPlayer(args[0]);
            commands.setGamemode(targetPlayer, args[0], player, GameMode.ADVENTURE, "adminessentials.adventure.others");

        } else if (args.length >= 2) {

            commandMessenger.wrongUsage(player, 1, "/gma <player>");
        } */

    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            commandMessenger.wrongUsage(sender, 0, "/gma <player>");
        } else if (args.length == 1) {

            Player targetPlayer = sender.getServer().getPlayer(args[0]);
            // commands.setGamemode(targetPlayer, args[0], sender, GameMode.ADVENTURE, "console");

        } else if (args.length >= 2) {

            commandMessenger.wrongUsage(sender, 1, "/gma <player>");
        }
    }

    public String getName() {
        return "gma";
    }

    public String getPermission() {
        return "adminessentials.adventure";
    }

    public String getPermission2() {
        return getPermission() + ".others";
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }

    public int maxArguments() {
        return 1;
    }

    public int minArguments() {
        return 0;
    }

    public int consoleMaxArguments() {
        return 1;
    }

    public int consoleMinArguments() {
        return 1;
    }

    /*
    private void setGamemode() {
        if (target.equals(sender)) {
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.setGameMode(gm);
        } else {
            target.setGameMode(gm);
            sender.sendMessage(ChatColor.GOLD + "You set " + ChatColor.RED + target.getName() + ChatColor.GOLD + " to " + gm + " mode!");
            target.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " set you to " + gm + " mode!");
        }
    } */

}
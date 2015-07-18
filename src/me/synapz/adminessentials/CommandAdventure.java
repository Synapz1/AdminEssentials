package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdventure extends AdminEssentialsCommand implements ConsoleCommand {

    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            Utils.setGamemode(player, player, GameMode.ADVENTURE);
        } else if (args.length == 1) {

            Player targetPlayer = player.getServer().getPlayer(args[0]);
            if (Utils.isPlayerOnline(player, args[0])) {
                Utils.setGamemode(player, targetPlayer, GameMode.ADVENTURE);
            }
        }
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player targetPlayer = sender.getServer().getPlayer(args[0]);

        if (Utils.isPlayerOnline(sender, args[0])) {
            Utils.setGamemode(sender, targetPlayer, GameMode.ADVENTURE);
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


}
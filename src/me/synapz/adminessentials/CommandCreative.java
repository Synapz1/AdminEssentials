package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandCreative extends AdminEssentialsCommand implements ConsoleCommand {

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : Bukkit.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        Utils.setGamemode(player, target, GameMode.CREATIVE);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (!Utils.isPlayerOnline(sender, args[0])) {
            return;
        }
        Utils.setGamemode(sender, target, GameMode.CREATIVE);
    }

    public String getName() {
        return "gmc";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.creative 0");
        permissions.add("adminessentials.creative.others 1");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0, 1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }
}
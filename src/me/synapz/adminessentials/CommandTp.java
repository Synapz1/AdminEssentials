package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

import java.util.ArrayList;

public class CommandTp extends AdminEssentialsCommand implements ConsoleCommand {

    private void teleport(CommandSender sender, Player target1, Player target2) {
        Utils.teleport(target1, target2);
        target1.sendMessage(GOLD + "Forced teleporting to " + RED + target2.getName());
        sender.sendMessage(GOLD + "Teleporting " + RED + target1.getName() + GOLD + " to " + target2.getName());
    }

    public void onCommand(Player player, String[] args) {
        Player target1 = player.getServer().getPlayer(args[0]);

        if (!Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        if (args.length == 1) {
            Utils.teleport(player, target1);
        } else if (args.length == 2) {
            Player target2 = player.getServer().getPlayer(args[1]);

            if (!Utils.isPlayerOnline(player, args[1])) {
                return;
            }
            teleport(player, target1, target2);
        }
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target1 = sender.getServer().getPlayer(args[0]);
        Player target2 = sender.getServer().getPlayer(args[1]);

        if (Utils.isPlayerOnline(sender, args[0]) && Utils.isPlayerOnline(sender, args[1])) {
            teleport(sender, target1, target2);
        }
    }

    public String getName() {
        return "tp";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.tp 1");
        permissions.add("adminessentials.tp.others 2");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1, 2);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(2);
    }

    public String[] getArguments() {
        return new String[] {"<player> [player]"};
    }

}

package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.objects.Jail;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

import java.util.ArrayList;

public class CommandSetJail extends AdminEssentialsCommand {

    public void onCommand(Player player, String[] args) {
        Jail jail = new Jail(args[0], player.getLocation());
        player.sendMessage(GOLD + "Created jail " + RED + jail.getName());
    }

    public String getName() {
        return "setjail";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.setjail 1");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<name>"};
    }
}

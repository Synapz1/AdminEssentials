package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandTppos extends AdminEssentialsCommand {

    private void tppos(String x, String y, String z, Player sender) {
        try {
            //Convert arguments to integers.
            int xloc = Integer.parseInt(x);
            int yloc = Integer.parseInt(y);
            int zloc = Integer.parseInt(z);
            Location cords = new Location(sender.getWorld(), xloc, yloc, zloc);
            sender.teleport(cords);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Make sure your coordinance are intergers!");
            sender.sendMessage(ChatColor.RED + "Usage: /tppos <x> <y> <z>");
        }
    }

    public void onCommand(Player player, String[] args) {
        tppos(args[0], args[1], args[3], player);
    }

    public String getName() {
        return "tppos";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.tppos 0");
        permissions.add("adminessentials.tppos 1");
        permissions.add("adminessentials.tppos 2");
        permissions.add("adminessentials.tppos 3");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(3);
    }

    public String[] getArguments() {
        return new String[] {"<x> <y> <z>"};
    }

}

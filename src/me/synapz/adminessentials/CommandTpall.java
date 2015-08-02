package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.util.Utils;
import static org.bukkit.ChatColor.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandTpall extends AdminEssentialsCommand {

    private void tpAll(Player sender) {
        for (Player p : sender.getServer().getOnlinePlayers()) {
            // check to make sure the player won't be teleported to themselves
            if (!p.getName().equals(sender.getName())) {
                Utils.teleport(p, sender);
            }
        }
        sender.sendMessage(GOLD + "All players have been teleported to you!");
    }

    public void onCommand(Player player, String[] args) {

    }

    public String getName() {
        return "tpall";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.tpall 0");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0);
    }

    public String[] getArguments() {
        return new String[] {""};
    }
}

package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandTphere extends AdminEssentialsCommand {

    public void onCommand(Player player, String[] args) {
        Player target = player.getServer().getPlayer(args[0]);
        if (!Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        Utils.teleport(target, player);
    }

    public String getName() {
        return "tphere";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.tphere 0");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }
}

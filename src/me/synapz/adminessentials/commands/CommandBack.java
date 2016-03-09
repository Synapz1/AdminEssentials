package me.synapz.adminessentials.commands;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.util.Config;

import java.util.ArrayList;

import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandBack extends AdminEssentialsCommand implements Listener {

    Config config = Config.getInstance();
    // TODO, if they have fly disabled Tp them to the ground.
    public void onCommand(Player player, String[] args) {
    	Location lastLoc = config.getLastLocation(player);

        if (lastLoc != null) {
            config.setLastLocation(player, player.getLocation());
            player.teleport(lastLoc);
            player.sendMessage(ChatColor.GOLD + "Teleporting...");
        } else {
            player.sendMessage(ChatColor.RED + "No /back location was found!");
        }
    }

    public String getName() {
    	return "back";
    }

    public ArrayList<String> getPermissions() {
    	ArrayList<String> permissions = new ArrayList<>();
    	permissions.add("adminessentials.back 0");
    	return permissions;
    }

    public ArrayList<Integer> handledArgs() {
    	return Utils.makeArgs(0);
    }

    public String[] getArguments() {
    	return new String[] {""};
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        config.setLastLocation(player, player.getLocation());
    }

    @EventHandler
    public void onTeleportation(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        config.setLastLocation(player, player.getLocation());
    }
}

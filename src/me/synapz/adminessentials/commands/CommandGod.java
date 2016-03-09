package me.synapz.adminessentials.commands;

import java.util.ArrayList;
import java.util.UUID;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CommandGod extends AdminEssentialsCommand implements ConsoleCommand, Listener {

    private static ArrayList<UUID> godPlayers = new ArrayList<>();

    private void god(CommandSender sender, Player target) {
        String action;
        if (godPlayers.contains(target.getUniqueId())) {
            godPlayers.remove(target.getUniqueId());
            action = RED + "disabled";
        } else {
            godPlayers.add(target.getUniqueId());
            action = RED + "enabled";
        }
        target.sendMessage(GOLD + "God mode " + action);
        Utils.sendSenderMessage(sender, target, GOLD + "God mode " + action + GOLD + " for " + RED + target.getName());
    }

    public void onCommand(Player player, String[] args) {
        Player target = args.length == 0 ? player : player.getServer().getPlayer(args[0]);
        if (args.length == 1 && !Utils.isPlayerOnline(player, args[0])) {
            return;
        }
        god(player, target);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        if (Utils.isPlayerOnline(sender, args[0])) {
            god(sender, target);
        }
    }

    public String getName() {
        return "god";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.god 0");
        permissions.add("adminessentials.god.others 1");
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

    @EventHandler
    public void onPlayerMove(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player)) {
            Player player = (Player)event.getEntity();
            if (godPlayers.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }
}
package me.synapz.adminessentials;

import java.util.ArrayList;
import java.util.UUID;

import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CommandGod extends AdminEssentialsCommand implements ConsoleCommand, Listener {

    private ArrayList<UUID> godPlayers = new ArrayList();

    private void god(CommandSender sender, Player target) {
        if (this.godPlayers.contains(target.getUniqueId())) {
            this.godPlayers.remove(target.getUniqueId());
            target.sendMessage(ChatColor.GOLD + "God mode disabled!");
            sender.sendMessage(ChatColor.GOLD + "God mode disabled for " + ChatColor.RED + target.getName());
        } else {
            this.godPlayers.add(target.getUniqueId());
            target.sendMessage(ChatColor.GOLD + "God mode enabled!");
            sender.sendMessage(ChatColor.GOLD + "God mode enabled for " + ChatColor.RED + target.getName());
        }
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
            if (this.godPlayers.contains(player.getUniqueId()))
                event.setCancelled(true);
        }
    }
}
package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandBack implements CommandExecutor, Listener {

    CommandUtil utils = new CommandUtil();
    CommandMessenger messenger = new CommandMessenger();
    Config config = Config.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        // permission checks
        if (sender instanceof Player || !(sender instanceof Player)) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(messenger.NO_CONSOLE_PERMS);
                return true;
            }
            if (!utils.permissionCheck(sender, "adminessentials.back")) {
                return true;
            }
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            Location lastLoc = config.getLastLocation(player);

            if (lastLoc != null) {
                config.setLastLocation(player, player.getLocation());
                player.teleport(lastLoc);
                player.sendMessage(ChatColor.GOLD + "Teleporting...");
            } else {
                player.sendMessage(ChatColor.RED + "No /back location was found!");
            }
        } else if (args.length >= 1) {
            messenger.wrongUsage(player, 1, "/back");
        }

        return false;
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

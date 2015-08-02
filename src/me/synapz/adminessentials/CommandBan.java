package me.synapz.adminessentials;

import java.util.ArrayList;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Config;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CommandBan extends AdminEssentialsCommand implements ConsoleCommand, Listener {
    private static final String DEFAULT_REASON = "You have been banned from the server!";
    private Config config = Config.getInstance();

    private boolean isPlayerOnline(Player target) {
        if (target == null) {
            return false; // not online
        }
        return true;
    }

    private String calculateReason(String[] args) {
        String banReason;

        if (args.length == 1) {
            banReason = DEFAULT_REASON;
        } else {
            banReason = Utils.messagerBuilder(args);
        }
        return banReason;
    }

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        OfflinePlayer offlineTarget;
        Player target = sender.getServer().getPlayer(args[0]);
        String banReason = calculateReason(args);

        if (isPlayerOnline(target)) { // player is online, kick + ban them as PLAYER
            config.setBanned(sender, target.getUniqueId().toString(), args[0], banReason, true);
            target.kickPlayer(banReason);
        } else {
            offlineTarget = sender.getServer().getOfflinePlayer(args[0]);
            config.setBanned(sender, offlineTarget.getUniqueId().toString(), args[0], banReason, true);
        }
    }

    public String getName() {
        return "ban";
    }

    public ArrayList<String> getPermissions() {
        return Utils.allPermArguments("adminessentials.ban");
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.allArguments();
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.allArguments();
    }

    public String[] getArguments() {
        return new String[] {"<player> [message]"};
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String kickMessage;

        if (config.isBanned(player)) {
            kickMessage = config.getBanReason(player);
            player.kickPlayer(kickMessage);
        }
    }
}
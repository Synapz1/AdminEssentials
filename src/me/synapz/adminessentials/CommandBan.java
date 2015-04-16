package me.synapz.adminessentials;

import java.util.ArrayList;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Config;
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

public class CommandBan implements Listener, CommandExecutor
{
    private static final String DEFAULT_REASON = "You have been banned from the server!";
    private CommandMessenger messenger = new CommandMessenger();
    private CommandUtil utils = new CommandUtil();
    private Config config;

    public CommandBan(Config c) {
        config = c;
    }

    private String messagerBuilder(String[] args) {
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            // if i-1 == args, its the last run so we need to remove the " " at the end
            msg = i+1 == args.length ? msg + args[i] : msg + args[i] + " ";
        }
        return msg;
    }


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
            banReason = messagerBuilder(args);
        }
        return banReason;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("ban")) {

            // quick permission check
            if (sender instanceof Player && !utils.permissionCheck(sender, "adminessentials.ban")) {
                return true;
            }

            if (args.length == 0) {

                messenger.wrongUsage(sender, 0, "/ban <player> [message]");

            } else if (args.length >= 1) {

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
        } else if (cmd.getName().equalsIgnoreCase("unban")) {

            if (sender instanceof Player && !utils.permissionCheck(sender, "adminessentials.unban")) {
                return true;
            }

            if (args.length == 0) {
                messenger.wrongUsage(sender, 0, "/unban <player>");
            } else if (args.length == 1) {

                Player target = sender.getServer().getPlayer(args[0]);

                if (isPlayerOnline(target)) {
                    config.setBanned(sender, target.getUniqueId().toString(), args[0], "", false);
                } else {
                    OfflinePlayer offlineTarget = sender.getServer().getOfflinePlayer(args[0]);
                    config.setBanned(sender, offlineTarget.getUniqueId().toString(), args[0], "", false);
                }

            } else if (args.length >= 2) {
                messenger.wrongUsage(sender, 1, "/unban <player>");
            }
        }

        return false;
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
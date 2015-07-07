package me.synapz.adminessentials;


import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandMute implements CommandExecutor, Listener {

    private CommandMessenger messenger = new CommandMessenger();
    private CommandUtil util = new CommandUtil();
    private Config config;

    public CommandMute(Config c) {
        config = c;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (((sender instanceof CommandSender)) && (cmd.getName().equalsIgnoreCase("mute"))) {
            // quick check to see if the sender is a player
            // if they are a player, check their permissions
            if (sender instanceof Player) {
                if (!util.permissionCheck(sender, "adminessentials.mute")) {
                    return true;
                }
            }

            if (args.length == 0) {
                messenger.wrongUsage(sender, 0, "/mute <player>");
            } else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (util.isPlayerOnline(sender, targetPlayer.getName())) {
                    if (config.isMuted(targetPlayer)) {
                        config.setMute(sender, targetPlayer, false, false);
                    } else // player isn't in config, so we add them to it
                    {
                        config.setMute(sender, targetPlayer, true, false);
                    }
                }
            } else if (args.length >= 2) {
                messenger.wrongUsage(sender, 1, "/mute <player>");
            }

        } else if (((sender instanceof CommandSender)) && (cmd.getName().equalsIgnoreCase("muteall"))) {
            if (sender instanceof Player) {
                if (!util.permissionCheck(sender, "adminessentials.muteall")) {
                    return true;
                }
            }

            if (args.length == 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!config.isMuted(p)) {
                        config.setMute(sender, p, true, true);
                        p.sendMessage(ChatColor.GOLD + "You were muted.");
                    }
                }
                sender.sendMessage(ChatColor.GOLD + "Muted all players!");
            } else if (args.length >= 1) {
                messenger.wrongUsage(sender, 1, "/muteall");
            }

        }


        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (config.isMuted(player)) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You are currently muted!");
            event.setCancelled(true);
        }
    }
}
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
import org.bukkit.event.player.PlayerMoveEvent;

public class CommandFreeze implements Listener, CommandExecutor

{

    CommandMessenger messenger = new CommandMessenger();
    CommandUtil util = new CommandUtil();
    Config config = null;

    public CommandFreeze(Config c) {
        config = c;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (((sender instanceof CommandSender)) && (cmd.getName().equalsIgnoreCase("freeze"))) {

            // quick check to see if the sender is a player
            // if they are a player, check their permissions
            if (sender instanceof Player) {
                if (!util.permissionCheck(sender, "adminessentials.freeze")) {
                    return true;
                }
            }

            if (args.length == 0) {
                messenger.wrongUsage(sender, 0, "/freeze <player>");
            } else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (util.isPlayerOnline(sender, targetPlayer, args[0])) {

                    if (config.isFrozen(targetPlayer)) {
                        config.setFreeze(sender, targetPlayer, false, false);
                    } else // player isn't in config, so we add them to it
                    {
                        config.setFreeze(sender, targetPlayer, true, false);
                    }
                }
            } else if (args.length >= 2) {
                messenger.wrongUsage(sender, 1, "/freeze <player>");
            }

        } else if (((sender instanceof CommandSender)) && (cmd.getName().equalsIgnoreCase("freezeall"))) {
            if (sender instanceof Player) {
                if (!util.permissionCheck(sender, "adminessentials.freezeall")) {
                    return true;
                }
            }

            if (args.length == 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!config.isFrozen(p)) {
                        config.setFreeze(sender, p, true, true);
                        p.sendMessage(ChatColor.GOLD + "You were frozen.");
                    }
                }
                sender.sendMessage(ChatColor.GOLD + "Frozen all players!");
            } else if (args.length >= 1) {
                messenger.wrongUsage(sender, 1, "/freezeall");
            }

        }

        return false;
    }

    @EventHandler
    public void onPlayerChat(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (config.isFrozen(player)) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You are currently frozen!");
            player.teleport(player);
        }
    }
}
package me.synapz.adminessentials;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class CommandBurn
        implements CommandExecutor
{
    CommandMessenger msg = new CommandMessenger();
    CommandUtil utils = new CommandUtil();

    public void burn(CommandSender sender, Player player, int ticks) {
        player.setFireTicks(ticks);
        player.sendMessage(ChatColor.GOLD + "You were set on fire for " + ChatColor.RED + (ticks / 20) + ChatColor.GOLD + " seconds!");
        sender.sendMessage(ChatColor.GOLD + "Set " + sender + " on fire for " + ChatColor.RED + (ticks / 20) + ChatColor.GOLD + " seconds!");
    }

    public void ext(CommandSender sender, Player player) {
        player.setFireTicks(0);
        player.sendMessage(ChatColor.GOLD + "You were extinguished!");

        if (!sender.getName().equals(player.getName())) {
            sender.sendMessage(ChatColor.GOLD + "You extinguished " + player.getName());
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ext")) {

            if (!(sender instanceof Player) && args.length == 0) {
                msg.wrongUsage(sender, 0, "/ext [player]");
                return true;
            }

            if (args.length == 0) {
                ext(sender, (Player) sender);
            } else if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (utils.isPlayerOnline(sender, args[0])) {
                    ext(sender, targetPlayer);
                }
            } else if (args.length >= 2) {
                msg.wrongUsage(sender, 1, "/ext [player]");
            }
        }

        else if (cmd.getName().equalsIgnoreCase("burn")) {
            if (args.length == 0) {
                msg.wrongUsage(sender, 0, "/burn <player> [seconds]");
            } else if (args.length == 1 || args.length == 2) {
                if (!utils.isPlayerOnline(sender, args[0])) {
                    return true;
                }

                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (args.length == 1) {
                    burn(sender, targetPlayer, 300);
                } else if (args.length == 2) {
                    int dur;
                    try {
                        dur = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + "Enter a valid integer!");
                        return true;
                    }
                    burn(sender, targetPlayer, dur);
                }
            } else if (args.length >= 3) {
                msg.wrongUsage(sender, 1, "/burn <player> [duration]");
            }
        }
        return false;
    }
}
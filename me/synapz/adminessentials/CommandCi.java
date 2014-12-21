package me.synapz.adminessentials;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class CommandCi
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if ((!(sender instanceof Player)) &&
                (cmd.getName().equalsIgnoreCase("ci"))) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /ci <player>");
            }
            if (args.length == 1) {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                }
                else {
                    targetPlayer.getInventory().clear();
                    sender.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " inventory!");
                    targetPlayer.sendMessage(ChatColor.GOLD + "Inventory cleared!");
                }
            }
            else if (args.length >= 2) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /ci <player>");
            }

        }

        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("ci"))
            {
                if (player.hasPermission("adminessentials.ci")) {
                    if (args.length == 0) {
                        player.getInventory().clear();
                        player.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.RED + player.getName() + ChatColor.GOLD + " inventory!");
                    }
                }
                else player.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!");

                if (player.hasPermission("adminessentials.ci.others")) {
                    if (args.length == 1) {
                        Player targetPlayer = player.getServer().getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GOLD + " wasn't found.");
                        }
                        else {
                            targetPlayer.getInventory().clear();
                            player.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.RED + targetPlayer.getName() + ChatColor.GOLD + " inventory!");
                            targetPlayer.sendMessage(ChatColor.GOLD + "Inventory cleared!");
                        }
                    }
                    else if (args.length >= 2) {
                        player.sendMessage(ChatColor.RED + " To many arguments!");
                        player.sendMessage(ChatColor.RED + "Usage: /ci <player>");
                    }

                }
                else if (args.length >= 1) {
                    player.sendMessage(ChatColor.DARK_RED + "You don't have access to this command!");
                }
            }
        }

        return false;
    }
}
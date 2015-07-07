package me.synapz.adminessentials;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandKillMobs implements CommandExecutor{

    private int mobsKilled;

    private void killMobs(){
        LivingEntity mobs;
        mobsKilled = 0;
        for (World world : (World[])Bukkit.getServer().getWorlds().toArray(new World[0])) {
            for (Iterator localIterator1 = world.getLivingEntities().iterator(); localIterator1.hasNext(); ) { mobs = (LivingEntity)localIterator1.next();
                if (!(mobs instanceof Player)) {
                    mobs.remove();
                    mobsKilled++;
                }
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {

        if ((cmd.getName().equalsIgnoreCase("killmobs")) && (!(sender instanceof Player))) {
            if (args.length == 0) {
                killMobs();
                sender.sendMessage(ChatColor.GOLD + "Killed " + ChatColor.RED + mobsKilled + " mobs!");
            }
            else if (args.length >= 1) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /killmobs");
            }
        }
        if ((cmd.getName().equalsIgnoreCase("killmobs")) && ((sender instanceof Player))) {
            Player player = (Player)sender;
            if (!player.hasPermission("adminessentials.killmobs")) {
                player.sendMessage(ChatColor.DARK_RED + "You don't have permission to that command!");
            }
            else if (args.length == 0) {
                killMobs();
                player.sendMessage(ChatColor.GOLD + "Killed " + ChatColor.RED + mobsKilled + " mobs!");
            }
            else if (args.length >= 1) {
                sender.sendMessage(ChatColor.RED + "To many arguments!");
                sender.sendMessage(ChatColor.RED + "Usage: /killmobs");
            }

        }

        return false;
    }
}
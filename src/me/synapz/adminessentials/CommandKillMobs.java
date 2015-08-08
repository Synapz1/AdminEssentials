package me.synapz.adminessentials;

import java.util.ArrayList;
import java.util.Iterator;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandKillMobs extends AdminEssentialsCommand implements ConsoleCommand {

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

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        killMobs();
        sender.sendMessage(ChatColor.GOLD + "Killed " + ChatColor.RED + mobsKilled + " mobs!");
    }

    public String getName() {
        return "killmobs";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.killmobs 0");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(0);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(0);
    }

    public String[] getArguments() {
        return new String[] {""};
    }
}
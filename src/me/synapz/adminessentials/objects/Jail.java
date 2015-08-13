package me.synapz.adminessentials.objects;

import me.synapz.adminessentials.util.Config;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.*;

public class Jail {

    private static List<Jail> jails = new ArrayList<>();
    Location location;
    String name;

    public static Jail getJail(String name) {
        for (Jail j : jails) {
            if (j != null && j.getName().equals(name)) {
                return j;
            }
        }
        return null;
    }

    public static boolean isJailNull(CommandSender sender, String name) {
        if (getJail(name) == null) {
            sender.sendMessage(GOLD + "Jail " + RED + name + GOLD + " cannot be found.");
            return true;
        } else {
            return false;
        }
    }


    public Jail(String name, Location loc) {
        this.name = name;
        this.location = loc;
    }

    public void delete() {
        // delete the instances and remove it from config
    }

    public void setTime(Player player, String time, String type) {

    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void jail(Player p) {
        p.teleport(this.getLocation());
        p.sendMessage(GOLD + "You have been " + RED + "jailed");
    }

    public void unjail(Player p) {
        p.teleport(Config.getInstance().getLastLocation(p));
    }

    public List<Jail> getJails() {
        return jails;
    }

    // listeners

    // disable removing blocks

    // disable teleportation

}

package me.synapz.adminessentials.objects;

import me.synapz.adminessentials.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

import static org.bukkit.ChatColor.*;

public class Jail {

    public enum TimeType {
        SECONDS,
        MINUTES,
        DAYS;
    }

    private static Map<String, Jail> jails = new HashMap<String, Jail>();
    Location location;
    String name;

    public static Jail getJail(String name) {
        return jails.get(name);
    }

    public static boolean isJailNull(CommandSender sender, String name) {
        if (getJail(name) == null) {
            sender.sendMessage(GOLD + "Jail " + RED + "'" + name + "'" + GOLD + " wasn't found.");
            return true;
        } else {
            return false;
        }
    }


    public Jail(String name, Location loc) {
        this.name = name;
        this.location = loc;

        Config.getInstance().createJail(name, loc);
        jails.put(name, this);
    }

    public void delete() {
        jails.remove(this);
        Config.getInstance().removeJail(this);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void jail(Player p) {
        Config.getInstance().addPlayerToJail(this, p);
        p.teleport(this.getLocation());
        p.sendMessage(GOLD + "You have been " + RED + "jailed" + GOLD + "!");
    }

    public void jail(Player p, int time, TimeType type) {
        Config.getInstance().addPlayerToJail(this, p, type, time);

        p.teleport(this.getLocation());
        p.sendMessage(GOLD + "You have been " + RED + "jailed" + GOLD + " for " + RED + time + " " + GOLD + type.toString().toLowerCase());
        // todo: implement a bukkit runnable
    }

    public void unjail(Player p) {
        p.teleport(Config.getInstance().getLastLocation(p, this));
        Config.getInstance().unjailPlayer(this, p);
    }

    public String getPath(String extra) {
        if (extra.equals("")) {
            return "Jails." + this.getName();
        }
        return "Jails." + this.getName() + "." + extra;
    }

    public static Map<String, Jail> getJails() {
        return jails;
    }

    // listeners

    // disable removing blocks

    // disable teleportation

}
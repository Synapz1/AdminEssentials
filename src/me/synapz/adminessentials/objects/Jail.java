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

    public static void loadJails() {
        FileConfiguration cache = Config.getInstance().getCacheFile();
        // In case there are no Jails, make the list empty
        if (cache.getConfigurationSection("Jails") == null) {
            cache.set("Jails", "");
        }

        Set<String> rawJailList = cache.getConfigurationSection("Jails").getKeys(false);
        for (String jail : rawJailList) {
            Object rawLoc = cache.get("Jails." + jail + ".Loc");
            Location loc = rawLoc == null || !(rawLoc instanceof Location) ? new Location(Bukkit.getWorlds().get(0), 0,0,0) : (Location) rawLoc;
            new Jail(jail, loc);
        }
    }

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

        boolean addToConfig = false;
        for (String jail : Config.getInstance().getCacheFile().getConfigurationSection("Jails").getKeys(false)) {
            if (jail.equalsIgnoreCase(name)) {
                addToConfig = true;
            }
        }

        if (addToConfig) {
            Config.getInstance().getCacheFile().set("Jails." + this.getName() + ".Loc", location);
            Config.getInstance().saveCache();
        }
        jails.put(name, this);
    }

    public void delete() {
        jails.remove(this);
        Config.getInstance().getCacheFile().set(this.getPath(""), null);
        Config.getInstance().saveCache();
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void jail(Player p) {
        Config.getInstance().getCacheFile().set(this.getPath("Players." + p.getUniqueId().toString() + ".Last-Loc"), p.getLocation());
        Config.getInstance().saveCache();

        p.teleport(this.getLocation());
        p.sendMessage(GOLD + "You have been " + RED + "jailed" + GOLD + "!");
    }

    public void jail(Player p, int time, TimeType type) {
        Config.getInstance().getCacheFile().set(this.getPath("Players." + p.getUniqueId().toString() + ".Last-Loc"), p.getLocation());
        Config.getInstance().getCacheFile().set(this.getPath("Players." + p.getUniqueId().toString() + "Time-Type"), type.toString());
        Config.getInstance().getCacheFile().set(this.getPath("Players." + p.getUniqueId().toString() + ".Duration-Left"), time);
        Config.getInstance().saveCache();

        p.teleport(this.getLocation());
        p.sendMessage(GOLD + "You have been " + RED + "jailed" + GOLD + " for " + RED + time + " " + GOLD + type.toString().toLowerCase());
        // todo: implement a bukkit runnable
    }

    public void unjail(Player p) {
        Config.getInstance().getCacheFile().set(this.getPath("Players." + p.getUniqueId().toString()), null);
        Config.getInstance().saveCache();

        p.teleport(Config.getInstance().getLastLocation(p));
    }

    private String getPath(String extra) {
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
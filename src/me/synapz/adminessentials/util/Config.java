package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import me.synapz.adminessentials.Metrics;
import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Config {


    private List<String> mutedPlayers;
    private List<String> frozenPlayers;
    private AdminEssentials ae;
    private FileConfiguration cache;
    private File cacheFile;
    private static Config instance;

    public enum AdminCommand {
        ANNOUNCE,
        BAN,
        GMA,
        GMS,
        GMSS,
        GMC,
        CI,
        FEED,
        FLY,
        FREEZE,
        GOD,
        MUTE,
        HEAL,
        KICK,
        KILL,
        KILLMOBS,
        MARCO,
        TP,
        TPHERE,
        VANISH;
    }

    public static Config getInstance() {
        return instance;
    }

    public Config(AdminEssentials a) {
        instance = this;
        this.ae = a;

        if (!a.getDataFolder().exists()) {
            a.getDataFolder().mkdir();
        }

        saveDefaultConfig();
        reloadCache();

        mutedPlayers = cache.getStringList("Players.Muted");
        frozenPlayers = cache.getStringList("Players.Frozen");
    }

    /**
     * Ban a player.
     * 1) Set it in config
     * 2) Ban / Unban them
     * 3) Send output to player & server
     * @param sender - sender of the command
     * @param uuid - uuid of the target
     * @param name - string name of the target
     * @param reason - reason for the ban
     * @param toBan - true/false (ban/unban)
     */
    public void setBanned(CommandSender sender, String uuid, String name, String reason, boolean toBan) {
        if (toBan) {
            cache.set("Players.Banned." + uuid, "");
            cache.set("Players.Banned." + uuid + ".Reason", reason);
        } else {
            /**
             * A Check to make sure we aren't unbanning a player who was
             * never banned
             */
            if (isBanned(sender.getServer().getOfflinePlayer(name)) || isBanned(sender.getServer().getPlayer(name))) {
                cache.set("Players.Banned." + uuid, null);
                Bukkit.broadcastMessage(GOLD + "Player " + RED + sender.getName() + GOLD + " unbanned " + RED + name);
            } else {
                sender.sendMessage(RED + name + GOLD + " is not banned!");
                return;
            }
        }
        saveCache();
    }

    /**
     * Check config to see if a player is banned.
     * @param player - Player to checked if baned
     * @return - true or false (currently banned or unbanned)
     */
    public boolean isBanned(Player player) {
        try {
            cache.get("Players.Banned." + player.getUniqueId().toString()).equals(player.getUniqueId().toString());
            return true;
        }catch (NullPointerException e) {
            // the player wasn't in the config therefore it throws a NPE, so we return false
            return false;
        }
    }

    /**
     * Check config to see if a OfflinePlayer is banned
     * @param player - OfflinePlayer to be checked
     * @return - true or false (currently banned or unbanned)
     */
    public boolean isBanned(OfflinePlayer player) {
        try {
            cache.get("Players.Banned." + player.getUniqueId().toString()).equals(player.getUniqueId().toString());
            return true;
        }catch (NullPointerException e) {
            // the player wasn't in the config therefore it throws a NPE, so we return false
            return false;
        }
    }

    /**
     * Get the ban reason of a player
     * @param player - player to get reason of
     * @return - reason
     */
    public String getBanReason(Player player) {
        return cache.getString("Players.Banned." + player.getUniqueId().toString() + ".Reason");
    }

    /**
     * Mute a player.
     * 1) Add/Remove them to the config.
     * 2) Send the output to players & server
     * @param sender - sender of the command
     * @param player - player to be muted
     * @param toMute - mute or to un mute (true/false)
     */
    public void setMute(CommandSender sender, Player player, boolean toMute) {
        if (toMute) {
            mutedPlayers.add(player.getUniqueId().toString());
        } else {
            mutedPlayers.remove(player.getUniqueId().toString());
        }

        cache.set("Players.Muted", mutedPlayers);
        String type = toMute ? "muted!" : "unmuted!";

        player.sendMessage(GOLD + "You have been " + RED + type);
        Utils.sendSenderMessage(sender, player, GOLD + "Player " + RED + player.getName() + GOLD + " was " + RED + type);

        saveCache();
    }

    /**
     * Mute a player.
     * 1) Add/Remove them to the config.
     * 2) Send the output to players & server
     * @param sender - sender of the command
     * @param target - player to be frozen
     * @param toFreeze - freeze or to un freeze (true/false)
     */
    public void setFreeze(CommandSender sender, Player target, boolean toFreeze) {
        if (toFreeze) {
            frozenPlayers.add(target.getUniqueId().toString());
        } else {
            frozenPlayers.remove(target.getUniqueId().toString());
        }

        cache.set("Players.Frozen", frozenPlayers);

        String type = toFreeze ? "frozen!" : "unfrozen!";

        Utils.sendSenderMessage(sender, target, GOLD + "Player " + RED + target.getName() + GOLD + " was " + RED + type );
        target.sendMessage(GOLD + "You have been " + RED + type);
        saveCache();
    }

    /**
     * Check the config to see if a player is muted.
     * @param player - player to be checked
     * @return - true (player is currently muted) false (player is frozen muted)
     */
    public boolean isMuted(Player player) {
        if (mutedPlayers.contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the config to see if a player is frozen.
     * @param player - player to be checked
     * @return - true (player is currently frozen) false (player is frozen muted)
     */
    public boolean isFrozen(Player player) {
        if (frozenPlayers.contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public void setLastLocation(Player player, Location loc) {
        cache.set("Last-locations." + player.getUniqueId(), loc);
    }

    public Location getLastLocation(Player player) {
        return (Location) cache.get("Last-locations." + player.getUniqueId());
    }

    public void saveCache() {
        if (cache == null || cacheFile == null) {
            return;
        }
        try {
            cache.save(cacheFile);
        } catch (IOException ex) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save AdminEssentials cache to " + cacheFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (cache == null) {
            cacheFile = new File(ae.getDataFolder(), "cache.yml");
        }
        if (!cacheFile.exists()) {
            ae.saveResource("cache.yml", false);
        }
    }

    public FileConfiguration getCacheFile() {
        if (cache == null) {
            reloadCache();
        }
        return cache;
    }

    public void reloadCache() {
        if (cacheFile == null) {
            cacheFile = new File(ae.getDataFolder(), "cache.yml");
        }
        cache = YamlConfiguration.loadConfiguration(cacheFile);

        // Look for defaults in the jar
        try {
            Reader defConfigStream = new InputStreamReader(ae.getResource("cache.yml"), "UTF8");
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            cache.setDefaults(defConfig);
        }catch (UnsupportedEncodingException e) {}
    }

}

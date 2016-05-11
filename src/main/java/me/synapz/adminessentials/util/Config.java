package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import me.synapz.adminessentials.objects.Jail;
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
import java.util.Set;
import java.util.logging.Level;

public class Config {

    public static boolean isChatStopped = false;

    private List<String> mutedPlayers;
    private List<String> frozenPlayers;
    private AdminEssentials ae;
    private FileConfiguration cache;
    private File cacheFile;
    private static Config instance;
    private boolean canUpdate = true;

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
        a.saveResource("config.yml", false);

        mutedPlayers = cache.getStringList("Players.Muted");
        frozenPlayers = cache.getStringList("Players.Frozen");

        try {
            isChatStopped = cache.getBoolean("is-chat-stopped");
        }catch (Exception e) {
            cache.set("is-chat-stopped", false);
        }
        loadValues(a.getConfig());
        a.saveConfig();
    }

    public boolean canUpdate() {
        if (canUpdate) {
            return true;
        } else {
            return false;
        }
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
                sender.sendMessage(GOLD + "Player " + RED + name + GOLD + " is not banned!");
                return;
            }
        }
        saveCache();
    }

    /**
     * Check config to see if a OfflinePlayer is banned
     * @param player - OfflinePlayer to be checked
     * @return - true or false (currently banned or unbanned)
     */
    public boolean isBanned(OfflinePlayer player) {
        String path = "Players.Banned." + player.getUniqueId().toString();
        return cache.get(path) == null ? false : true;
    }

    /**
     * Get the ban reason of a player
     * @param player - player to get reason of
     * @return - reason
     */
    public String getBanReason(OfflinePlayer player) {
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

    public Location getLastLocation(Player player, Jail jail) {
        Object rawLoc = cache.get(jail.getPath("Players." + player.getUniqueId() + ".Last-Loc"));
        Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
        if (loc instanceof Location) {
            loc = (Location) rawLoc;
        }
        return loc;
    }

    public void setIsChatStopped(CommandSender sender) {
        if (isChatStopped) {
            sender.sendMessage(GOLD + "You have " + RED + "enabled " + GOLD + "chat");
            isChatStopped = false;
        } else {
            sender.sendMessage(GOLD + "You have " + RED + "disabled " + GOLD + "chat");
            isChatStopped = true;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(sender.getName())) {
                p.sendMessage(GOLD + "Chat has been " + RED + (isChatStopped ? "disabled" : "enabled"));
            }
        }
        cache.set("is-chat-stopped", isChatStopped);
        saveCache();
    }

    public void loadJails() {
        // In case there are no Jails, make the list empty
        if (!cache.isConfigurationSection("Jails")) {
            cache.createSection("Jails");
            saveCache();
        }

        Set<String> rawJailList = cache.getConfigurationSection("Jails").getKeys(false);
        for (String jail : rawJailList) {
            Object rawLoc = cache.get("Jails." + jail + ".Loc");
            Location loc = rawLoc == null || !(rawLoc instanceof Location) ? new Location(Bukkit.getWorlds().get(0), 0,0,0) : (Location) rawLoc;
            new Jail(jail, loc);
        }
    }

    public void createJail(String name, Location loc) {
        if (cache.get("Jails." + name) == null) {
            cache.set("Jails." + name + ".Loc", loc);
            saveCache();
        }
    }

    public void removeJail(Jail jail) {
        cache.set(jail.getPath(""), null);
        saveCache();
    }

    public void addPlayerToJail(Jail jail, Player p) {
        cache.set(jail.getPath("Players." + p.getUniqueId().toString() + ".Last-Loc"), p.getLocation());
        saveCache();
    }

    public void addPlayerToJail(Jail jail, Player p, Jail.TimeType type, int time) {
        cache.set(jail.getPath("Players." + p.getUniqueId().toString() + ".Last-Loc"), p.getLocation());
        cache.set(jail.getPath("Players." + p.getUniqueId().toString() + ".Time-Type"), type.toString());
        cache.set(jail.getPath("Players." + p.getUniqueId().toString() + ".Duration-Left"), time);
        saveCache();
    }

    public void unjailPlayer(Jail jail, Player p) {
        cache.set(jail.getPath("Players." + p.getUniqueId().toString()), null);
        saveCache();
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

    private void loadValues(FileConfiguration config) {
        if (!config.contains("auto-update")) {
            config.set("auto-update", true);
        }
        canUpdate = config.getBoolean("auto-update");
    }


}

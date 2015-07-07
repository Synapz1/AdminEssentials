package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import me.synapz.adminessentials.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.List;
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
            } else {
                sender.sendMessage(CommandMessenger.PLAYER_NOT_BANNED);
                return;
            }
        }

        // print output
        CommandMessenger.onBan(sender, name, reason, toBan);

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
     * @param muteAll - a mute all check to block spam
     */
    public void setMute(CommandSender sender, Player player, boolean toMute, boolean muteAll) {
        if (toMute) {
            mutedPlayers.add(player.getUniqueId().toString());
        } else {
            mutedPlayers.remove(player.getUniqueId().toString());
        }

        cache.set("Players.Muted", mutedPlayers);

        if (!muteAll) {
            CommandMessenger.onMute(sender, player, toMute);
        }
        saveCache();
    }

    /**
     * Mute a player.
     * 1) Add/Remove them to the config.
     * 2) Send the output to players & server
     * @param sender - sender of the command
     * @param player - player to be frozen
     * @param toFreeze - freeze or to un freeze (true/false)
     * @param freezeAll - a freeze all check to block spam
     */
    public void setFreeze(CommandSender sender, Player player, boolean toFreeze, boolean freezeAll) {
        if (toFreeze) {
            frozenPlayers.add(player.getUniqueId().toString());
        } else {
            frozenPlayers.remove(player.getUniqueId().toString());
        }

        cache.set("Players.Frozen", frozenPlayers);

        if (!freezeAll) {
            CommandMessenger.onFreeze(sender, player, toFreeze);
        }
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

    public void setupMetrics() {
        try {
            Metrics metrics = new Metrics(ae);

            Metrics.Graph commandsGraph = metrics.createGraph("Most used commands");

            for (final AdminCommand command: AdminCommand.values()) {
                commandsGraph.addPlotter(new Metrics.Plotter(getName(command)) {
                    @Override
                    public int getValue() {
                        return cache.getInt("command-counter." + getName(command).toLowerCase());
                    }
                });
            }
            metrics.start();
        } catch (IOException e) {}
    }

    public void increment(AdminCommand command) {
        String path = getName(command).toLowerCase();
        cache.set("command-counter." + path, cache.getInt("command-counter." + path) + 1);
        saveCache();
    }

    public String getName(AdminCommand command) {
        String name = "";
        switch (command) {
            case ANNOUNCE:
                name = "Announce";
                break;
            case BAN:
                name = "Ban";
                break;
            case GMA:
                name = "GMA";
                break;
            case GMS:
                name = "GMS";
                break;
            case GMSS:
                name = "GMSS";
                break;
            case GMC:
                name = "GMC";
                break;
            case CI:
                name = "Ci";
                break;
            case FEED:
                name = "Feed";
                break;
            case FLY:
                name = "Fly";
                break;
            case FREEZE:
                name = "Freeze";
                break;
            case GOD:
                name = "God";
                break;
            case MUTE:
                name = "Mute";
                break;
            case HEAL:
                name = "Heal";
                break;
            case KICK:
                name = "Kick";
                break;
            case KILL:
                name = "Kill";
                break;
            case KILLMOBS:
                name = "Killmobs";
                break;
            case MARCO:
                name = "Marco";
                break;
            case TP:
                name = "Tp";
                break;
            case TPHERE:
                name = "Tphere";
                break;
            case VANISH:
                name = "Vanish";
                break;
        }
        return name;
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

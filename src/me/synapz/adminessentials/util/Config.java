package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Config {


    private List<String> mutedPlayers;
    private List<String> frozenPlayers;
    private AdminEssentials ae;
    private FileConfiguration config;

    public Config(AdminEssentials a) {
        ae = a;
        ae.saveConfig();
        config = ae.getConfig();

        mutedPlayers = config.getStringList("Players.Muted");
        frozenPlayers = config.getStringList("Players.Frozen");
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
            config.set("Players.Banned." + uuid, "");
            config.set("Players.Banned." + uuid + ".Reason", reason);
        } else {
            /**
             * A Check to make sure we aren't unbanning a player who was
             * never banned
             */
            if (isBanned(sender.getServer().getOfflinePlayer(name)) || isBanned(sender.getServer().getPlayer(name))) {
                config.set("Players.Banned." + uuid, null);
            } else {
                sender.sendMessage(CommandMessenger.PLAYER_NOT_BANNED);
                return;
            }
        }

        // print output
        CommandMessenger.onBan(sender, name, reason, toBan);

        ae.saveConfig();
    }

    /**
     * Check config to see if a player is banned.
     * @param player - Player to checked if baned
     * @return - true or false (currently banned or unbanned)
     */
    public boolean isBanned(Player player) {
        try {
            config.get("Players.Banned." + player.getUniqueId().toString()).equals(player.getUniqueId().toString());
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
            config.get("Players.Banned." + player.getUniqueId().toString()).equals(player.getUniqueId().toString());
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
        return config.getString("Players.Banned." + player.getUniqueId().toString() + ".Reason");
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

        config.set("Players.Muted", mutedPlayers);

        if (!muteAll) {
            CommandMessenger.onMute(sender, player, toMute);
        }
        ae.saveConfig();
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

        config.set("Players.Frozen", frozenPlayers);

        if (!freezeAll) {
            CommandMessenger.onFreeze(sender, player, toFreeze);
        }
        ae.saveConfig();
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


}

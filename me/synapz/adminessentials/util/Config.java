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

    public void setBanned(CommandSender sender, String uuid, String name, String reason, boolean toBan) {

        if (toBan) {
            config.set("Players.Banned." + uuid, "");
            config.set("Players.Banned." + uuid + ".Reason", reason);
        } else {
            config.set("Players.Banned." + uuid, null);
        }

        // print output
        CommandMessenger.onBan(sender, name, reason, toBan);

        ae.saveConfig();
    }


    public boolean isBanned(Player player) {
        try {
            config.get("Players.Banned." + player.getUniqueId().toString()).equals(player.getUniqueId().toString());
            return true;
        }catch (NullPointerException e) {
            // the player wasn't in the config therefore it throws a NPE, so we return false
            return false;
        }
    }

    public String getBanReason(Player player) {
        return config.getString("Players.Banned." + player.getUniqueId().toString() + ".Reason");
    }

    /*
     * Store a player into config and print information
     * to the sender & player to access later
     */
    public void setMute(CommandSender sender, Player player, boolean toMute, boolean muteAll) {
        if (toMute) {
            mutedPlayers.add(player.getUniqueId().toString());
        } else {
            mutedPlayers.remove(player.getUniqueId().toString());
        }

        // sync the config with the list
        config.set("Players.Muted", mutedPlayers);

        // used to prevent spam to player during a muteall, says "Muted all" instead of Muted <player> a lot
        if (!muteAll) {
            CommandMessenger.onMute(sender, player, toMute);
        }
        ae.saveConfig();
    }

    /*
     * Store a player into config and print information
     * to the sender & player to access later
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

    /*
     * @return is the player muted or not
     */
    public boolean isMuted(Player player) {
        if (mutedPlayers.contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * @return is the player frozen or not
     */
    public boolean isFrozen(Player player) {
        if (frozenPlayers.contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }


}

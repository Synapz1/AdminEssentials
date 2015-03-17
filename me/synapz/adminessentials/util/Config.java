package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Config {

    AdminEssentials am;

    /*
     * Initialize am during onEnable proccess
     */
    public Config(AdminEssentials a)
    {
        // initialize the am var to access config methods
        am = a;
    }

    /*
     * Store a player into config and print information
     * to the sender & player to access later
     */
    public void setMute(CommandSender sender, Player player, boolean toMute)
    {
        // set config values
        am.getConfig().set("Players." + player.getUniqueId() + ".Name", player.getName());
        am.getConfig().set("Players." + player.getUniqueId() + ".Muted", toMute);

        // print output to player and console
        CommandMessenger.onMute(sender, player, toMute);

        am.saveConfig();
    }

    /*
     * Store a player into config and print information
     * to the sender & player to access later
     */
    public void setFreeze(CommandSender sender, Player player, boolean toFreeze)
    {
        // set config values
        am.getConfig().set("Players." + player.getUniqueId() + ".Name", player.getName());
        am.getConfig().set("Players." + player.getUniqueId() + ".Frozen", toFreeze);

        // print output to player and console
        CommandMessenger.onFreeze(sender, player, toFreeze);

        am.saveConfig();
    }

    /*
     * @return is the player muted or not
     */
    public boolean isMuted(Player player)
    {
        try {
            return am.getConfig().getBoolean("Players." + player.getUniqueId() + ".Muted");
        }catch(NullPointerException e) {
            // player was never added to file, therefore it's null so we return false
            return false;
        }
    }

    /*
     * @return is the player frozen or not
     */
    public boolean isFrozen(Player player)
    {
        try {
            return am.getConfig().getBoolean("Players." + player.getUniqueId() + ".Frozen");
        }catch(NullPointerException e) {
            // player was never added to file, therefore it's null so we return false
            return false;
        }    }

}

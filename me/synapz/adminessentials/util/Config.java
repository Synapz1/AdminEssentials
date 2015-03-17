package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Config {

    AdminEssentials am;
    List<String> mutedPlayers;
    List<String> frozenPlayers;

    /*
     * Initialize am during onEnable proccess
     */
    public Config(AdminEssentials a)
    {
        // initialize the am var to access config methods
        am = a;

        // add all the muted players to the mutedPLayers list
        // todo: check if null
        mutedPlayers = am.getConfig().getStringList("Players.Muted");
        frozenPlayers = am.getConfig().getStringList("Players.Frozen");
    }

    /*
     * Store a player into config and print information
     * to the sender & player to access later
     */
    public void setMute(CommandSender sender, Player player, boolean toMute)
    {
        if(toMute)
        {
            mutedPlayers.add(player.getUniqueId().toString());
            am.getConfig().set("Players.Muted", mutedPlayers);
        }
        else
        {
            mutedPlayers.remove(player.getUniqueId().toString());
            am.getConfig().set("Players.Muted", mutedPlayers);
        }

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
        if (toFreeze)
        {
            frozenPlayers.add(player.getUniqueId().toString());
            am.getConfig().set("Players.Frozen", frozenPlayers);
        }
        else
        {
            frozenPlayers.remove(player.getUniqueId().toString());
            am.getConfig().set("Players.Frozen", frozenPlayers);
        }


        CommandMessenger.onFreeze(sender, player, toFreeze);
        am.saveConfig();
    }

    /*
     * @return is the player muted or not
     */
    public boolean isMuted(Player player)
    {
        if (mutedPlayers.contains(player.getUniqueId().toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
     * @return is the player frozen or not
     */
    public boolean isFrozen(Player player)
    {
        if (frozenPlayers.contains(player.getUniqueId().toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}

package me.synapz.adminessentials.util;


import me.synapz.adminessentials.AdminEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Config {

    AdminEssentials am;

    public Config(AdminEssentials a)
    {
        am = a;
    }

    public void setMute(CommandSender sender, Player player, boolean muted)
    {
        am.getConfig().set("Players." + player.getUniqueId() + ".Name", player.getName());
        am.getConfig().set("Players." + player.getUniqueId() + ".Muted", muted);
        CommandMessenger.onMute(sender, player, muted);
        am.saveConfig();
    }

    public void setFreeze(Player player, boolean freeze)
    {
        am.getConfig().set("Players." + player.getUniqueId() + ".Name", player.getName());
        am.getConfig().set("Players." + player.getUniqueId() + ".Frozen", freeze);
    }

    public boolean getMute(Player player)
    {
        try{
            return am.getConfig().getBoolean("Players." + player.getUniqueId() + ".Muted");
        }catch(NullPointerException e) {
            // player was never added to file, therefore it's null so we return false
            return false;
        }
    }

    public boolean getFreeze(Player player)
    {
        return am.getConfig().getBoolean("Players." + player.getUniqueId() + ".Frozen");
    }

    public boolean playersMuted()
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            UUID uuid = p.getUniqueId();
            if(am.getConfig().contains("Players." + uuid))
            {
                if(getMute(p))
                return true;
            }
        }
        return false;
    }

}

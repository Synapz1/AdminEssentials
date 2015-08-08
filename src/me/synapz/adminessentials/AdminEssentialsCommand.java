package me.synapz.adminessentials;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public abstract class AdminEssentialsCommand {

    public abstract void onCommand(Player player, String[] args);

    public abstract String getName();

    public abstract ArrayList<String> getPermissions();

    public abstract ArrayList<Integer> handledArgs();

    public abstract String[] getArguments();

    public String getCorrectUsage() {
        String arguments = "";
        for (String arg : getArguments()) {
            arguments += arg + " ";
        }
        return ChatColor.RED + "Usage: /" + this.getName() + " " + arguments;
    }
}

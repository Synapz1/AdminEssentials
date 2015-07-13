package me.synapz.adminessentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public abstract class AdminEssentialsCommand {

    public abstract void onCommand(Player player, String[] args);

    public abstract String getName();

    public abstract String getPermission();

    public abstract String getPermission2();

    public abstract int maxArguments();

    public abstract int minArguments();

    public abstract String[] getArguments();

    public String getCorrectUsage() {
        String arguments = "";
        for (String arg : getArguments()) {
            arguments += arg + " ";
        }
        return ChatColor.RED + "Usage: /" + this.getName() + " " + arguments;
    }

    public String getSecondPermission() {
        return getPermission() + ".others";
    }

}

package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Config;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class CommandMute extends AdminEssentialsCommand implements ConsoleCommand, Listener {

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        Utils.mute(sender, target, Utils.isPlayerOnline(sender, args[0]), false);
    }

    public String getName() {
        return "mute";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.mute 0");
        return permissions;
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.makeArgs(1);
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.makeArgs(1);
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (Config.getInstance().isMuted(player)) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You are currently muted!");
            event.setCancelled(true);
        }
    }
}
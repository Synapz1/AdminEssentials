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
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class CommandFreeze extends AdminEssentialsCommand implements ConsoleCommand, Listener {

    private Config config = Config.getInstance();

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);
        if (Utils.isPlayerOnline(sender, args[0])) {
            if (config.isFrozen(target)) {
                config.setFreeze(sender, target, false);
            } else // player isn't in config, so we add them to it
            {
                config.setFreeze(sender, target, true);
            }
        }
    }

    public String getName() {
        return "freeze";
    }

    public ArrayList<String> getPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("adminessentials.freeze 1");
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
    public void onPlayerChat(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (config.isFrozen(player)) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You are currently frozen!");
            player.teleport(player);
        }
    }
}
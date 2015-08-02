package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.base.ConsoleCommand;
import me.synapz.adminessentials.util.Config;
import me.synapz.adminessentials.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.RED;

public class CommandUnban extends AdminEssentialsCommand implements ConsoleCommand {

    private Config config = Config.getInstance();

    private boolean isPlayerOnline(Player target) {

        if (target == null) {
            return false; // not online
        }
        return true;
    }

    public void onCommand(Player player, String[] args) {
        onConsoleCommand(player, args);
    }

    public void onConsoleCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);

        if (isPlayerOnline(target)) {
            config.setBanned(sender, target.getUniqueId().toString(), args[0], "", false);
        } else {
            OfflinePlayer offlineTarget = sender.getServer().getOfflinePlayer(args[0]);
            config.setBanned(sender, offlineTarget.getUniqueId().toString(), args[0], "", false);
        }
        Bukkit.broadcastMessage(GOLD + "Player " + RED + sender.getName() + GOLD + " unbanned " + RED + args[0]);
    }

    public String getName() {
        return "unban";
    }

    public ArrayList<String> getPermissions() {
        return Utils.allPermArguments("adminessentials.unban");
    }

    public ArrayList<Integer> handledArgs() {
        return Utils.allArguments();
    }

    public ArrayList<Integer> consoleHandledArgs() {
        return Utils.allArguments();
    }

    public String[] getArguments() {
        return new String[] {"<player>"};
    }
}

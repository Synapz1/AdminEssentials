package me.synapz.adminessentials;

import java.util.ArrayList;
import java.util.UUID;

import me.synapz.adminessentials.util.CommandMessenger;
import me.synapz.adminessentials.util.CommandUtil;
import me.synapz.adminessentials.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandMute
        implements CommandExecutor, Listener
{
    ArrayList<UUID> mute = new ArrayList<UUID>();
    CommandMessenger messenger = new CommandMessenger();
    CommandUtil util = new CommandUtil();
    AdminEssentials am;
    Config config;

    public CommandMute(AdminEssentials e)
    {
        am = e;
        config = new Config(am);
;    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        CommandUtil util = new CommandUtil();
        config = new Config(am);

        if (((sender instanceof CommandSender)) && (cmd.getName().equalsIgnoreCase("mute")))
        {
            // quick check to see if the sender is a player
            // if they are a player, check their permissions
            if (sender instanceof Player)
            {
                if(!util.permissionCheck(sender, "adminessentials.mute"))
                {
                    return true;
                }
            }

            if (args.length == 0)
            {
                messenger.wrongUsage(sender, 0, "/mute <player>");
            }
            else if (args.length == 1)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[0]);
                if (util.isPlayerOnline(sender, targetPlayer, args[0]))
                {
                    if(config.getMute(targetPlayer))
                    {
                        config.setMute(sender, targetPlayer, false);
                    }
                    else // player isn't in config, so we add them to it
                    {
                        config.setMute(sender, targetPlayer, true);
                    }
                }
            }
            else if (args.length >= 2)
            {
                messenger.wrongUsage(sender, 1, "/mute <player>");
            }

        }

        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(config.playersMuted())
        {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "You are currently muted!");
            event.setCancelled(true);
        }
    }
}
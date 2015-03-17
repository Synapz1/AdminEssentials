package me.synapz.adminessentials;

import java.io.IOException;

import me.synapz.adminessentials.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminEssentials extends JavaPlugin {

    CommandMute mute = new CommandMute(this);
    CommandFreeze freeze = new CommandFreeze();
    CommandGod god = new CommandGod();
    CommandTeleportation tp = new CommandTeleportation();

    //added tppos
    // added vanish
    //added spectator mode
    // dramatically fixed code structure
    @Override
    public void onEnable()
    {
        saveConfig();
        registerEvents();
        registerCommands();

        Config c = new Config(this);
        CommandMute m = new CommandMute(this);

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException localIOException) {
        }
    }

    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(mute, this);
        pm.registerEvents(god, this);
        pm.registerEvents(freeze, this);
    }

    private void registerCommands()
    {
        getCommand("mute").setExecutor(mute);
        getCommand("muteall").setExecutor(mute);
        getCommand("god").setExecutor(god);
        getCommand("freeze").setExecutor(freeze);
        getCommand("freezeall").setExecutor(freeze);
        getCommand("ban").setExecutor(new CommandBan());
        getCommand("unban").setExecutor(new CommandBan());
        getCommand("kill").setExecutor(new CommandKill());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("feed").setExecutor(new CommandFeed());
        getCommand("tp").setExecutor(tp);
        getCommand("tphere").setExecutor(tp);
        getCommand("tppos").setExecutor(tp);
        getCommand("tpall").setExecutor(tp);
        getCommand("burn").setExecutor(new CommandBurn());
        getCommand("ext").setExecutor(new CommandBurn());
        getCommand("gmc").setExecutor(new CommandCreative());
        getCommand("gms").setExecutor(new CommandSurvival());
        getCommand("gma").setExecutor(new CommandAdventure());
        getCommand("gmss").setExecutor(new CommandSpectator());
        getCommand("fly").setExecutor(new CommandFly());
        getCommand("marco").setExecutor(new CommandMarco());
        getCommand("ci").setExecutor(new CommandCi());
        getCommand("announce").setExecutor(new CommandAnnounce());
        getCommand("kick").setExecutor(new CommandKick());
        getCommand("kickall").setExecutor(new CommandKick());
        getCommand("killall").setExecutor(new CommandKill());
        getCommand("killmobs").setExecutor(new CommandKillMobs());
        getCommand("v").setExecutor(new CommandVanish());
    }


}
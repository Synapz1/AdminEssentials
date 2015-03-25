package me.synapz.adminessentials;

import java.io.IOException;

import me.synapz.adminessentials.util.Config;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminEssentials extends JavaPlugin {
    CommandMute m;
    CommandFreeze f;
    CommandBan b;
    Config c;

    @Override
    public void onEnable() {
        c = new Config(this);
        m = new CommandMute(c);
        f = new CommandFreeze(c);
        b = new CommandBan(c);

        registerEvents();
        registerCommands();

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException localIOException) {}

    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(m, this);
        pm.registerEvents(b, this);
        pm.registerEvents(new CommandGod(), this);
        pm.registerEvents(f, this);
    }

    private void registerCommands() {
        getCommand("mute").setExecutor(m);
        getCommand("muteall").setExecutor(m);
        getCommand("god").setExecutor(new CommandGod());
        getCommand("freeze").setExecutor(f);
        getCommand("freezeall").setExecutor(f);
        getCommand("ban").setExecutor(b);
        getCommand("unban").setExecutor(b);
        getCommand("kill").setExecutor(new CommandKill());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("feed").setExecutor(new CommandFeed());
        getCommand("tp").setExecutor(new CommandTeleportation());
        getCommand("tphere").setExecutor(new CommandTeleportation());
        getCommand("tppos").setExecutor(new CommandTeleportation());
        getCommand("tpall").setExecutor(new CommandTeleportation());
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
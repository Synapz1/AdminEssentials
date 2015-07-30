package me.synapz.adminessentials;

import java.io.IOException;

import me.synapz.adminessentials.util.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminEssentials extends JavaPlugin implements CommandExecutor {

    // todo: add teleport cooldown

    @Override
    public void onEnable() {
        Config c = new Config(this);

        CommandManager.getManager().init();

        registerEvents();
        registerCommands();

        c.setupMetrics();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new CommandMute(), this);
        pm.registerEvents(new CommandBan(), this);
        pm.registerEvents(new CommandGod(), this);
        pm.registerEvents(new CommandFreeze(), this);
        pm.registerEvents(new CommandBack(), this);
    }

    private void registerCommands() {
        getCommand("mute").setExecutor(new CommandMute());
        getCommand("muteall").setExecutor(new CommandMute());
        getCommand("god").setExecutor(new CommandGod());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("freezeall").setExecutor(new CommandFreeze());
        getCommand("ban").setExecutor(new CommandBan());
        getCommand("unban").setExecutor(new CommandBan());
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
        getCommand("gma").setExecutor(CommandManager.getManager());
        getCommand("gmss").setExecutor(new CommandSpectator());
        getCommand("fly").setExecutor(new CommandFly());
        getCommand("marco").setExecutor(new CommandMarco());
        getCommand("ci").setExecutor(new CommandCi());
        getCommand("announce").setExecutor(CommandManager.getManager());
        getCommand("kick").setExecutor(new CommandKick());
        getCommand("kickall").setExecutor(new CommandKick());
        getCommand("killall").setExecutor(new CommandKill());
        getCommand("killmobs").setExecutor(new CommandKillMobs());
        getCommand("v").setExecutor(new CommandVanish());
        getCommand("back").setExecutor(new CommandBack());
    }
}

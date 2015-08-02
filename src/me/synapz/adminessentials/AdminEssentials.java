package me.synapz.adminessentials;

import me.synapz.adminessentials.util.Config;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminEssentials extends JavaPlugin implements CommandExecutor {

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
        for (AdminEssentialsCommand cmd : CommandManager.getManager().getAllCommands()) {
            getCommand(cmd.getName()).setExecutor(CommandManager.getManager());
        }
        getCommand("mute").setExecutor(new CommandMute());
        getCommand("muteall").setExecutor(new CommandMute());
        getCommand("god").setExecutor(new CommandGod());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("freezeall").setExecutor(new CommandFreeze());
        // getCommand("ban").setExecutor(CommandManager.getManager());
        // getCommand("unban").setExecutor(CommandManager.getManager());
        getCommand("kill").setExecutor(new CommandKill());
        getCommand("heal").setExecutor(new CommandHeal());
        // getCommand("feed").setExecutor(CommandManager.getManager());
        getCommand("tp").setExecutor(new CommandTeleportation());
        getCommand("tphere").setExecutor(new CommandTeleportation());
        getCommand("tppos").setExecutor(new CommandTeleportation());
        getCommand("tpall").setExecutor(new CommandTeleportation());
        // getCommand("burn").setExecutor(new CommandBurn());
        // getCommand("ext").setExecutor(new CommandBurn());
        // getCommand("gmc").setExecutor(CommandManager.getManager());
        getCommand("gms").setExecutor(new CommandSurvival());
        // getCommand("gma").setExecutor(CommandManager.getManager());
        getCommand("gmss").setExecutor(new CommandSpectator());
        // getCommand("fly").setExecutor(CommandManager.getManager());
        getCommand("marco").setExecutor(new CommandMarco());
        // getCommand("ci").setExecutor(CommandManager.getManager());
        // getCommand("announce").setExecutor(CommandManager.getManager());
        getCommand("kick").setExecutor(new CommandKick());
        getCommand("kickall").setExecutor(new CommandKick());
        getCommand("killall").setExecutor(new CommandKill());
        getCommand("killmobs").setExecutor(new CommandKillMobs());
        getCommand("v").setExecutor(new CommandVanish());
        // getCommand("back").setExecutor(CommandManager.getManager());
    }
}
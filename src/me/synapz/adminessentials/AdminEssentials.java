package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.manager.CommandManager;
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
        // getCommand("mute").setExecutor(CommandManager.getManager());
        // getCommand("muteall").setExecutor(CommandManager.getManager());
        // getCommand("god").setExecutor(CommandManager.getManager());
        // getCommand("freeze").setExecutor(CommandManager.getManager());
        // getCommand("ban").setExecutor(CommandManager.getManager());
        // getCommand("unban").setExecutor(CommandManager.getManager());
        // getCommand("kill").setExecutor(CommandManager.getManager());
        // getCommand("heal").setExecutor(CommandManager.getManager());
        // getCommand("feed").setExecutor(CommandManager.getManager());
        // getCommand("tp").setExecutor(CommandManager.getManager());
        // getCommand("tphere").setExecutor(CommandManager.getManager());
        // getCommand("tppos").setExecutor(CommandManager.getManager());
        // getCommand("tpall").setExecutor(CommandManager.getManager());
        // getCommand("burn").setExecutor(new CommandBurn());
        // getCommand("ext").setExecutor(new CommandBurn());
        // getCommand("gmc").setExecutor(CommandManager.getManager());
        // getCommand("gms").setExecutor(CommandManager.getManager());
        // getCommand("gma").setExecutor(CommandManager.getManager());
        // getCommand("gmss").setExecutor(CommandManager.getManager());
        // getCommand("fly").setExecutor(CommandManager.getManager());
        // getCommand("marco").setExecutor(CommandManager.getManager());
        // getCommand("ci").setExecutor(CommandManager.getManager());
        // getCommand("announce").setExecutor(CommandManager.getManager());
        // getCommand("kick").setExecutor(CommandManager.getManager());
        // getCommand("kickall").setExecutor(CommandManager.getManager());
        // getCommand("killall").setExecutor(CommandManager.getManager());
        // getCommand("killmobs").setExecutor(CommandManager.getManager());
        // getCommand("v").setExecutor(CommandManager.getManager());
        // getCommand("back").setExecutor(CommandManager.getManager());
    }
}
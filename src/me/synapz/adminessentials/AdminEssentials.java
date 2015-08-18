package me.synapz.adminessentials;

import me.synapz.adminessentials.base.AdminEssentialsCommand;
import me.synapz.adminessentials.commands.*;
import me.synapz.adminessentials.manager.CommandManager;
import me.synapz.adminessentials.util.Config;
import me.synapz.adminessentials.util.Metrics;
import me.synapz.adminessentials.util.Updater;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AdminEssentials extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        Config c = new Config(this);

        CommandManager.getManager().init();

        registerEvents();
        registerCommands();

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {}

        // Updater updater = new Updater(this, 76238, this.getFile(), Updater.UpdateType.DEFAULT, true);
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
    }
}
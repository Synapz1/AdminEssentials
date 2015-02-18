package me.synapz.adminessentials;

import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminEssentials extends JavaPlugin {


    CommandFreeze commandFreeze = new CommandFreeze();
    CommandMute commandMute = new CommandMute();
    CommandGod commandGod = new CommandGod();

    //added tppos
    //added spectator mode
    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this.commandMute, this);
        getCommand("mute").setExecutor(commandMute);
        getCommand("muteall").setExecutor(commandMute);
        this.getServer().getPluginManager().registerEvents(this.commandGod, this);
        getCommand("god").setExecutor(commandGod);
        this.getServer().getPluginManager().registerEvents(this.commandFreeze, this);
        getCommand("freeze").setExecutor(commandFreeze);
        getCommand("freezeall").setExecutor(commandFreeze);

        getCommand("ban").setExecutor(new CommandBan());
        getCommand("unban").setExecutor(new CommandBan());
        getCommand("kill").setExecutor(new CommandKill());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("feed").setExecutor(new CommandFeed());
        getCommand("tp").setExecutor(new CommandTp());
        getCommand("tphere").setExecutor(new CommandTp());
        getCommand("tppos").setExecutor(new CommandTp());
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
        getCommand("tpall").setExecutor(new CommandTpall());

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException localIOException) {
        }
    }

    @Override
    public void onDisable()
    {


    }


}


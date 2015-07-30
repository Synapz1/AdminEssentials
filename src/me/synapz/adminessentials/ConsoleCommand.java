package me.synapz.adminessentials;


import org.bukkit.command.CommandSender;

public interface ConsoleCommand {

    public abstract void onConsoleCommand(CommandSender sender, String[] args);

    public abstract int[] consoleHandledArgs();

}

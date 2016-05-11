package me.synapz.adminessentials.base;


import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public interface ConsoleCommand {

    void onConsoleCommand(CommandSender sender, String[] args);

    ArrayList<Integer> consoleHandledArgs();
}

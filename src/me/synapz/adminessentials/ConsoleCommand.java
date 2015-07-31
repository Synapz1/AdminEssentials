package me.synapz.adminessentials;


import org.bukkit.command.CommandSender;

import java.util.ArrayList;

interface ConsoleCommand {

    void onConsoleCommand(CommandSender sender, String[] args);

    ArrayList<Integer> consoleHandledArgs();
}

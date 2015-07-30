package me.synapz.adminessentials;


import static org.bukkit.ChatColor.*;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    ArrayList<AdminEssentialsCommand> commands = new ArrayList<>();
    private static CommandManager manager = new CommandManager();

    private CommandManager() {}

    public static CommandManager getManager() {
        return manager;
    }

    public void init() {
        addCommands(new CommandAdventure(), new CommandAnnounce());
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        boolean hasConsoleSupport = false;
        boolean isAECommand = false;
        AdminEssentialsCommand command = null;

        for (AdminEssentialsCommand aec : commands) {
            if (cmd.getName().equalsIgnoreCase(aec.getName())) {
                command = aec;
                isAECommand = true;

                // todo: test to make sure this works
                if (command instanceof ConsoleCommand) {
                	hasConsoleSupport = true;
                }
                /*try {
                    ConsoleCommand consoleCommand = (ConsoleCommand) command;
                    consoleCommand.consoleMaxArguments();
                    hasConsoleSupport = true;
                }catch (ClassCastException e) {
                    // console does not have support so leave hasConsoleSupport false
                } */
            }
        }

        if (!isAECommand) {
            return true;
        }

        if (sender instanceof Player) {
            try {
                if (commandChecks(command, sender, args.length)) {
                    command.onCommand((Player) sender, args);
                }
            }catch (Exception e) {
                sender.sendMessage(RED + "An unknown error occurred. Check console for a error log.");
                e.printStackTrace();
            }
        }

        if (sender instanceof ConsoleCommandSender || sender instanceof CommandBlock) {
            if (hasConsoleSupport) {
                try {
                    if (commandChecks(command, sender, args.length)) {
                        ConsoleCommand consoleCommand = (ConsoleCommand) command;
                        consoleCommand.onConsoleCommand(sender, args);
                    }
                } catch (Exception e) {
                    sender.sendMessage(RED + "An unknown error occurred. Check console for a error log.");
                    e.printStackTrace();
                }

            } else {
                sender.sendMessage(RED + "Console is not permitted to use that command!");
            }
        }
        return true;
    }

    private boolean commandChecks(AdminEssentialsCommand command, CommandSender sender, int argCount) {
    	if (isCorrectArgs(sender, command, argCount) && !(sender instanceof Player)) {
    		return true;
    	}
    	if (isCorrectArgs(sender, command, argCount) && hasPerm(sender, argCount, command) && sender instanceof Player) {
    		return true;
    	}
    	return false;
    }
    
    private boolean hasPerm(CommandSender sender, int argument, AdminEssentialsCommand command) {
        // todo, check here to see what happens if the argument is not in the getpermissions, like 65 when there is only 2.
    	// Edit: Contains key will work for this. TODO: Test this...
    	if (command.getPermissions().containsKey(argument) && sender.hasPermission(command.getPermissions().get(argument))) {
    		return true;
    	} else {
            sender.sendMessage(DARK_RED + "You don't have access to that command!");
    		return false;
    	}
    }

    private boolean isCorrectArgs(CommandSender sender, AdminEssentialsCommand command, int argCount) {
        boolean correctArgCount = false;
        
        if (command instanceof ConsoleCommand && !(sender instanceof Player)) {
        	ConsoleCommand consoleCommand = (ConsoleCommand) command;
        	for (int i : consoleCommand.consoleHandledArgs()) {
        		if (i == argCount) {
        			correctArgCount = true;
        			break;
        		}
        	}
        } else {
        	for (int i : command.handledArgs()) {
        		if (i == argCount) {
        			correctArgCount = true;
        			break;
        		}
        	}
        }
        if (!correctArgCount) {
        	sender.sendMessage(DARK_RED + "Please review your argument count.");
        	sender.sendMessage(DARK_RED + command.getCorrectUsage());
        }
        return correctArgCount;
    }

    private void addCommands(AdminEssentialsCommand...cmds) {
        for (AdminEssentialsCommand cmd : cmds) {
            commands.add(cmd);
        }
    }
}

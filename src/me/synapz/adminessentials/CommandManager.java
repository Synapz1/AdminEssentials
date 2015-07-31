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
        addCommands(new CommandAdventure(), new CommandAnnounce(), new CommandBack());
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
                if (command instanceof ConsoleCommand) {
                	hasConsoleSupport = true;
                }
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
    	if (!(sender instanceof Player) && isCorrectArgs(sender, command, argCount)) {
    		return true;
    	}
    	if (sender instanceof Player && isCorrectArgs(sender, command, argCount) && hasPerm(sender, argCount, command)) {
    		return true;
    	}
    	return false;
    }
    
    private boolean hasPerm(CommandSender sender, int argument, AdminEssentialsCommand command) {
        String permission = "";
        for (String perm : command.getPermissions()) {
            // makes a list, so "adminessentials.survival 1" becomes permList[0] = "adminessentials.survival" permList[1] = "1"
            String[] permList = perm.split(" ");
            // turn the int part to an Integer from string and check if the argument is = to argument param, if it is set permList[0] to the permission
            if (Integer.parseInt(permList[1]) == argument) {
                permission = permList[0];
            }
        }
        if (sender.hasPermission(permission)) {
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
            if (consoleCommand.consoleHandledArgs().contains(argCount)) {
                correctArgCount = true;
            }
        } else {
            if (command.handledArgs().contains(argCount)) {
                correctArgCount = true;
            }
        }

        if (!correctArgCount) {
        	sender.sendMessage(RED + "Please review your argument count.");
        	sender.sendMessage(RED + command.getCorrectUsage());
        }
        return correctArgCount;
    }

    private void addCommands(AdminEssentialsCommand...cmds) {
        for (AdminEssentialsCommand cmd : cmds) {
            commands.add(cmd);
        }
    }
}

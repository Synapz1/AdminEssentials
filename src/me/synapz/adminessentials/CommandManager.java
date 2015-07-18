package me.synapz.adminessentials;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager implements CommandExecutor {

    private enum DispatchType {
        CONSOLE,
        PLAYER;
    }

    ArrayList<AdminEssentialsCommand> commands = new ArrayList<>();
    private static CommandManager manager = new CommandManager();

    private CommandManager() {}

    public static CommandManager getManager() {
        return manager;
    }

    public void init() {
        addCommands(new CommandAdventure());
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

                try {
                    ConsoleCommand consoleCommand = (ConsoleCommand) command;
                    consoleCommand.consoleMaxArguments();
                    hasConsoleSupport = true;
                }catch (ClassCastException e) {
                    // console does not have support so leave hasConsoleSupport false
                }
            }
        }

        if (!isAECommand) {
            return true;
        }

        if (sender instanceof Player) {
            try {
                if (commandChecks(command, sender, DispatchType.PLAYER, args.length)) {
                    command.onCommand((Player) sender, args);
                }
            }catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "An unknown error occurred. Check console for a error log.");
                e.printStackTrace();
            }
        }

        if (sender instanceof ConsoleCommandSender || sender instanceof CommandBlock) {
            if (hasConsoleSupport) {
                try {
                    if (commandChecks(command, sender, DispatchType.CONSOLE, args.length)) {
                        ConsoleCommand consoleCommand = (ConsoleCommand) command;
                        consoleCommand.onConsoleCommand(sender, args);
                    }
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "An unknown error occurred. Check console for a error log.");
                    e.printStackTrace();
                }

            } else {
                sender.sendMessage(ChatColor.RED + "Console is not permitted to use that command!");
            }
        }
        return true;
    }

    private boolean commandChecks(AdminEssentialsCommand command, CommandSender sender, DispatchType type, int argCount) {
        if (type == DispatchType.CONSOLE) {
            if (!argumentCheck(sender, command, type, argCount)) {
                return false;
            }
        } else if (type == DispatchType.PLAYER) {
            Player player = (Player) sender;
            // todo: stop sending so many double messages on hasPerm
            if (!hasPerm(player, command.getPermission(), command.minArguments(), argCount) || !hasPerm(player, command.getPermission2(), command.maxArguments(), argCount) && command.maxArguments() == argCount || !argumentCheck(player, command, type, argCount)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPerm(CommandSender sender, String perm, int argToUsePermission, int args) {
        if (!sender.hasPermission(perm) && argToUsePermission == args && sender instanceof Player) {
            sender.sendMessage(ChatColor.DARK_RED + "You don't have access to that command!" + perm);
            return false;
        }
        return true;
    }

    private boolean argumentCheck(CommandSender sender, AdminEssentialsCommand command, DispatchType type, int argCount) {
        ConsoleCommand consoleCommand = null;

        if (type == DispatchType.CONSOLE) {
            consoleCommand = (ConsoleCommand) command;
        }

        if (consoleCommand != null && argCount < consoleCommand.consoleMinArguments() || argCount < command.minArguments()) {
                sender.sendMessage(ChatColor.RED + "Not enough arguments!");
                sender.sendMessage(command.getCorrectUsage());
                return false;
            } else if (consoleCommand != null && argCount > consoleCommand.consoleMaxArguments() || argCount > command.maxArguments()) {
                sender.sendMessage(ChatColor.RED + "Too many arguments!");
                sender.sendMessage(command.getCorrectUsage());
                return false;
            }
            return true;
    }


    private void addCommands(AdminEssentialsCommand...cmds) {
        for (AdminEssentialsCommand cmd : cmds) {
            commands.add(cmd);
        }
    }
}

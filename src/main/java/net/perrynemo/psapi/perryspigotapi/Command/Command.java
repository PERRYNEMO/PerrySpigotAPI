package net.perrynemo.psapi.perryspigotapi.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Command implements CommandExecutor, TabCompleter {

    private final CommandInfo commandInfo;
    protected final JavaPlugin main;

    public Command(JavaPlugin main) {
        this.main = main;
        this.commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "require command info");
    }
    public CommandInfo getCommandInfo() {
            return commandInfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!commandInfo.permissions().isEmpty()) {
            if (!sender.hasPermission(commandInfo.permissions())) {
                sender.sendMessage("You don't have permission to execute this command");
                return true;
            }
        }
        if (!commandInfo.requirePlayer()) {
            execute(sender, args);
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("executor must be a player");
            return  true;
        }
        execute((Player) sender , args);
        return true;
    }
    public void execute(CommandSender sender, String[] args){}
    public void execute(Player player, String[] args){}

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> completors = new ArrayList<>();
        if (!commandInfo.permissions().isEmpty()) {
            if (!sender.hasPermission(commandInfo.permissions())) {
                sender.sendMessage("You don't have permission to execute this command");
                return completors;
            }
        }
        if (!commandInfo.requirePlayer()) {
            return tabComplete(sender, args);
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("executor must be a player");
            return  completors;
        }
        return tabComplete((Player) sender, args);
    }
    public List<String> tabComplete(CommandSender sender, String[] args){return null;}
    public List<String> tabComplete(Player pLayer, String[] args){return null;}
}

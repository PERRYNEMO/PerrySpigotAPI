package net.perrynemo.psapi.perryspigotapi.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public abstract class Command implements CommandExecutor {

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
}

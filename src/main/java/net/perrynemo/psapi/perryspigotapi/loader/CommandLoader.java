package net.perrynemo.psapi.perryspigotapi.loader;

import net.perrynemo.psapi.perryspigotapi.Command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CommandLoader extends PerryLoader{

    private static CommandLoader instance;
    private List<Class<? extends Command>> commands= new ArrayList<>();

    private CommandLoader(JavaPlugin main) {
        super(main);
    }

    @Override
    protected String getName() {
        return "Command Loader";
    }

    @Override
    protected void loadDataFromMain() {}

    @Override
    protected void loadDataFromConfig() {}

    @Override
    protected void generateResponce() {
        commands.forEach(clazz -> {
            try {
                Command command = clazz.getConstructor(JavaPlugin.class).newInstance(main);
                final PluginCommand pluginCommand = main.getCommand(command.getCommandInfo().name());
                assert pluginCommand != null;
                pluginCommand.setExecutor(command);
                pluginCommand.setTabCompleter(command);
                logger.log(Level.INFO, "generate command : "+ command.getCommandInfo().name());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public static CommandLoader getInstance(JavaPlugin main) {
        if(instance == null) instance = new CommandLoader(main);
        return instance;
    }
    public void addCommand(Class<? extends Command> clazz) {
        commands.add(clazz);
    }
}
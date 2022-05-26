package net.perrynemo.psapi.perryspigotapi.loader;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PerryLoader {

    protected final JavaPlugin main;
    protected final Logger logger;

    protected PerryLoader (JavaPlugin main) {
        this.main = main;
        logger = main.getLogger();
    }

    public void load() {
        logger.log(Level.INFO, getName() +" starting to load data");
        loadDataFromMain();
        loadDataFromConfig();
        generateResponce();
    }

    protected abstract String getName();
    protected abstract void loadDataFromMain();
    protected abstract void loadDataFromConfig();
    protected abstract void generateResponce();

}

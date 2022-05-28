package net.perrynemo.psapi.perryspigotapi.loader;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EventLoader extends PerryLoader{

    private static EventLoader eventLoader;
    private final List<Class<?>> classes;

    protected EventLoader(JavaPlugin main) {
        super(main);
        classes = new ArrayList<>();
    }

    @Override
    protected String getName() {
        return "Event loader";
    }

    @Override
    protected void loadDataFromMain() {

    }

    @Override
    protected void loadDataFromConfig() {

    }

    @Override
    protected void generateResponce() {
        classes.forEach(clazz -> {
            try {
                Listener listener = (Listener) clazz.getConstructor().newInstance();
                main.getServer().getPluginManager().registerEvents(listener, main);
                logger.log(Level.INFO, "define listener : " + listener.getClass().getSimpleName());
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static EventLoader getEventLoader(JavaPlugin javaPlugin) {
        if(eventLoader ==null) eventLoader = new EventLoader(javaPlugin);
        return eventLoader;
    }

    public void addClass(Class<?> clazz) {
        classes.add(clazz);
    }

    public void removeClass(Class<?> clazz) {
        classes.remove(clazz);
    }
}

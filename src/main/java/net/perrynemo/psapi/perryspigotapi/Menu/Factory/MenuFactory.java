package net.perrynemo.psapi.perryspigotapi.Menu.Factory;

import net.perrynemo.psapi.perryspigotapi.Menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuFactory {

    private static final Map<String, Menu> menus = new HashMap<>();


    public void openMenu(Player pLayer){
        Menu menu = createMenu();
        menu.open(pLayer.getName());
    }
    public abstract Menu createMenu();

    protected static void addMenu(String string,Menu menu){
        menus.put(string, menu);
    }

    protected static boolean contain(String string){
        return menus.containsKey(string);
    }
    public static Menu getMenu(String name){
        return menus.get(name);
    }
}

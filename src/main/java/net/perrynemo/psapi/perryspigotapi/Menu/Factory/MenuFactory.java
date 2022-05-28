package net.perrynemo.psapi.perryspigotapi.Menu.Factory;

import net.perrynemo.psapi.perryspigotapi.Menu.Menu;
import org.bukkit.entity.Player;

public abstract class MenuFactory {

    public void openMenu(Player pLayer){
        Menu menu = createMenu();
        menu.open(pLayer.getName());
    }
    public abstract Menu createMenu();
}

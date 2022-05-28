package net.perrynemo.psapi.perryspigotapi.Event;


import net.perrynemo.psapi.perryspigotapi.Menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {



    @EventHandler
    public void Onclickinv(InventoryClickEvent e) {
        if(e.getClickedInventory()==null) return;
        Player p = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(!(holder instanceof Menu)) return;
        e.setCancelled(true);
        if(e.getCurrentItem()==null)return;
        Menu menu = (Menu) holder;
        menu.ClickonMenu(e);
    }
}

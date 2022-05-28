package net.perrynemo.psapi.perryspigotapi.Menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public abstract class PaginedMenu extends Menu{

    protected int page = 0;
    protected int maxitem = 45;
    protected int index = 0;

    @Override
    public void setMenuItems() {
        addMenu();
    }

    public void addMenu(){
        inventory.setItem(maxitem+3, getItemStack(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "Left",1));

        inventory.setItem(maxitem+4, getItemStack(Material.BARRIER, ChatColor.DARK_RED + "Close",1));

        inventory.setItem(maxitem+5, getItemStack(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "Right",1));

    }

}

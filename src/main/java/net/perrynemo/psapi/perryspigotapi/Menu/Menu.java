package net.perrynemo.psapi.perryspigotapi.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected int slot;

    public void open(String playerName) {
        slot = getSlots();
        inventory = Bukkit.createInventory(this, slot, getMenuName());
        setMenuItems();
        if(Bukkit.getPlayer(playerName)==null) return;
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).openInventory(inventory);
    }


    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract  void ClickonMenu(InventoryClickEvent e);
    public abstract  void setMenuItems();

    public ItemStack getItemStack(Material material, String name, int number){
        ItemStack itemStack = new ItemStack(material,number);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

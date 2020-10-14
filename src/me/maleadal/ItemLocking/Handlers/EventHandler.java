package me.maleadal.ItemLocking.Handlers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;


public class EventHandler implements Listener {
    @org.bukkit.event.EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        ItemStack itemClicked = event.getCurrentItem();
        try{
            if(itemClicked.getItemMeta().getEnchants().containsKey(Enchantment.getByKey(EnchantHandler.lock.getKey()))){
                if(event.isLeftClick() || event.getAction() == InventoryAction.PICKUP_ONE || event.getAction() == InventoryAction.HOTBAR_SWAP || event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD){
                    event.setResult(Event.Result.DENY);
                    event.setCancelled(true);
                }
            }
        }catch(NullPointerException e){
            System.out.println("No Enchant");
        }

    }
    @org.bukkit.event.EventHandler
    public static void onDropEvent(PlayerDropItemEvent event){
        ItemStack itemDropped = event.getItemDrop().getItemStack();
        if(itemDropped.getEnchantments().containsKey(Enchantment.getByKey(EnchantHandler.lock.getKey()))){
            event.setCancelled(true);
        }
    }

}

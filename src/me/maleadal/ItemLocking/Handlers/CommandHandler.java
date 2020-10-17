package me.maleadal.ItemLocking.Handlers;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("&7Only Players can run ItemLocking commands");
            return true;
        }
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("lock")){
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if(heldItem.getType() == Material.AIR){
                player.sendMessage("§7You need to an item to lock");
            }
            else if(heldItem.containsEnchantment(EnchantHandler.lock)){
                player.sendMessage("§7This item is already locked!");
            }
            else{
                ItemMeta meta = heldItem.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                meta.addEnchant(EnchantHandler.lock, EnchantHandler.lock.getStartLevel(), true);
                lore.add("§b---§lLOCKED---§b§l");
                meta.setLore(lore);
                heldItem.setItemMeta(meta);
                player.sendMessage("§7Locked");
            }   
        }else if(cmd.getName().equalsIgnoreCase("unlock")){
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if(heldItem.getItemMeta().getEnchants().containsKey(Enchantment.getByKey(EnchantHandler.lock.getKey()))){
                ItemMeta meta = heldItem.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                meta.removeEnchant(EnchantHandler.lock);
                meta.setLore(lore);
                heldItem.setItemMeta(meta);
                player.sendMessage("§7Unlocked");
            }
            else{
                player.sendMessage("§7This item is already unlocked!");
            }
        }
        return true;

    }
}
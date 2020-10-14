package me.maleadal.ItemLocking.Handlers;

import me.maleadal.ItemLocking.CustomLockEnchant.Lock;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EnchantHandler {
    public static Lock lock;

    public static void init(){
        lock = new Lock("lock");
        registerEnchant(lock);
    }
    public static void de_init(){
        unRegisterEnchant(lock);
    }

    private static void registerEnchant(Enchantment enchantment){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            registered = false;
            e.printStackTrace();
        }
        if(registered){

        }
    }
    private static void unRegisterEnchant(Enchantment enchant){
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(enchant.getKey())) {
                byKey.remove(enchant.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(enchant.getName())) {
                byName.remove(enchant.getName());
            }
        } catch (Exception ignored) { }
    }
}

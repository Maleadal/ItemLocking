package me.maleadal.ItemLocking;

import me.maleadal.ItemLocking.Handlers.CommandHandler;
import me.maleadal.ItemLocking.Handlers.EnchantHandler;
import me.maleadal.ItemLocking.Handlers.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Locking extends JavaPlugin {
    private static Locking plugin;


    @Override
    public void onEnable(){
        CommandHandler command = new CommandHandler();
        EventHandler event = new EventHandler();
        plugin = this;
        EnchantHandler.init();
        getCommand("lock").setExecutor(command);
        getCommand("unlock").setExecutor(command);
        getCommand("play").setExecutor(command);
        getServer().getPluginManager().registerEvents(event, this);
        getServer().getConsoleSender().sendMessage("§1[ItemLocking]: §2Enabled" + ChatColor.RESET);
    }
    @Override
    public void onDisable(){
        EnchantHandler.de_init();
        getServer().getConsoleSender().sendMessage("§1[ItemLocking]: §3Disabled" + ChatColor.RESET);
    }

    public static Locking getPlugin(){return plugin;}
}

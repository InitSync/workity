package me.bryangaming.labority;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Labority extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCore pluginCore = new PluginCore(this);
        pluginCore.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

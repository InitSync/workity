package me.bryang.workity.plugin.loader;

import me.bryang.workity.plugin.utils.LogUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class EventLoader {
	private EventLoader() {}
	
	public static class Builder {
		private final JavaPlugin plugin;
		
		private Listener[] listeners;
		
		public Builder(JavaPlugin plugin) {
			this.plugin = Objects.requireNonNull(plugin, "The JavaPlugin instance is null.");
		}
		
		public Builder providers(Listener... listeners) {
			this.listeners = Objects.requireNonNull(listeners, "The Listener array is null.");
			return this;
		}
		
		public void build() {
			if (listeners == null) {
				LogUtils.error("The Listener array is null to be used.");
				return;
			}
			
			PluginManager pluginManager = Bukkit.getPluginManager();
			for (Listener listener : listeners) pluginManager.registerEvents(listener, plugin);
		}
	}
}

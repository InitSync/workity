package me.bryang.workity.loader;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
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
			if (listeners == null) throw new NullPointerException("The Listener array is null to be used.");
			
			Arrays.asList(listeners).forEach(listener -> {
				plugin.getServer()
					.getPluginManager()
					.registerEvents(listener, plugin);
			});
		}
	}
}

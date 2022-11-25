package me.bryang.workity.plugin.listeners;

import me.bryang.workity.api.loaders.DataModelLoader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;

public class PlayerRegistryListener
implements Listener {
	private final DataModelLoader dataLoader;
	
	public PlayerRegistryListener(DataModelLoader dataLoader) {
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
	}
	
	@EventHandler
	public void joinRegistryData(PlayerJoinEvent event) {
		UUID playerId = event.getPlayer().getUniqueId();
		
		if (dataLoader.playerJob(playerId) == null) dataLoader.createPlayerJob(playerId);
	}
}

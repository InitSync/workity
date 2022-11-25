package me.bryang.workity.plugin.listeners;

import me.bryang.workity.Workity;
import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;
import java.util.UUID;

public class WorkFurnaceListener implements Listener {
	private final BukkitConfigurationHandler configurationHandler;
	private final Database database;
	private final DataModelLoader dataLoader;
	
	public WorkFurnaceListener(
		 BukkitConfigurationHandler configurationHandler,
		 Database database,
		 DataModelLoader dataLoader) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader instance is null.");
	}
	
	@EventHandler
	private void onFurnaceJob(FurnaceExtractEvent event) {
		if (event.getBlock().getType() != Material.STONE) return;
		
		UUID playerId = event.getPlayer().getUniqueId();
		
		for (String key : configurationHandler.configSection("config.yml", "jobs").getKeys(false)) {
			if (!configurationHandler.text("config.yml", "config." + key + ".type").equals("PLAYER_BREAK_BLOCK")) continue;
			
			if (!database.playerJobs(playerId).contains(key)) continue;
			
			if (!dataLoader.job(key).blockJobData().containsKey("STONE")) continue;
			
			event.getBlock().setMetadata("workity::furnace", new FixedMetadataValue(Workity.instance(), true));
		}
	}
}

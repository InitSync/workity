package me.bryang.workity.listeners;

import me.bryang.workity.Workity;
import me.bryang.workity.database.Database;
import me.bryang.workity.loader.DataLoader;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public class WorkFurnaceListener implements Listener {
	private final BukkitConfigurationHandler configurationHandler;
	private final Database database;
	
	private final DataLoader dataLoader;
	
	public WorkFurnaceListener(
		BukkitConfigurationHandler configurationHandler,
		Database database,
		DataLoader dataLoader
	) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader instance is null.");
	}
	
	@EventHandler
	private void onFurnaceJob(FurnaceExtractEvent event) {
		Block block = event.getBlock();
		if (block.getType() != Material.STONE) return;
		
		for (String key : configurationHandler.configSection("config.yml", "jobs").getKeys(false)) {
			if (!configurationHandler.text("config.yml", "config." + key + ".type").equals("PLAYER_BREAK_BLOCK")) continue;
			
			if (!database.playerJobs(event.getPlayer().getUniqueId()).contains(key)) continue;
			
			if (!dataLoader.job(key)
				.blockJobData()
				.containsKey("STONE")
			) {
				continue;
			}
			
			event.getBlock().setMetadata("workity::furnace", new FixedMetadataValue(Workity.instance(), true));
		}
	}
}

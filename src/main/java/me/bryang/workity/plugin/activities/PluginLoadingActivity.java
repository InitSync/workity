package me.bryang.workity.plugin.activities;

import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.api.models.ActivityModel;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.utils.MathLevelsUtils;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PluginLoadingActivity
implements ActivityModel {
	private final BukkitConfigurationHandler configurationHandler;
	private final DataModelLoader dataLoader;
	private final Database database;
	
	public PluginLoadingActivity(
		 BukkitConfigurationHandler configurationHandler,
		 Database database,
		 DataModelLoader dataLoader) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler instance is null.");
		this.database = Objects.requireNonNull(database, "The Database instance is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
	}
	
	@Override
	public void load() {
		List<String> playersList = database.playersList();
		if (playersList == null) return;
		
		playersList.forEach(player -> {
			UUID playerId = UUID.fromString(player);
			dataLoader.createPlayerJob(playerId);
			
			for (String jobName : database.playerJobs(playerId)) {
				PlayerJobData playerJobData = new PlayerJobData(jobName);
				
				playerJobData.setLevel(database.jobIntData(playerId, jobName, "xp"));
				playerJobData.setLevel(database.jobIntData(playerId, jobName, "level"));
				playerJobData.setMaxExp(MathLevelsUtils.calculateNumber(configurationHandler.text("config.yml", "config.formula.max-xp"), 1));
				
				dataLoader.playerJob(playerId).putJob(jobName, playerJobData);
			}
		});
	}
}

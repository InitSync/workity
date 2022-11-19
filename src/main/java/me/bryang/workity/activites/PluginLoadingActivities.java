package me.bryang.workity.activites;

import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.database.Database;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.utils.MathLevelsUtils;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PluginLoadingActivities implements Activities {
	private final BukkitConfigurationHandler configurationHandler;
	private final DataLoader dataLoader;
	private final Database database;
	
	public PluginLoadingActivities(
		BukkitConfigurationHandler configurationHandler,
		Database database,
		DataLoader dataLoader
	) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler instance is null.");
		this.database = Objects.requireNonNull(database, "The Database instance is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
	}
	
	public void loadTask() {
		List<String> playersList = database.playersList();
		if (playersList == null) return;
		
		playersList.forEach(player -> {
			UUID playerId = UUID.fromString(player);
			dataLoader.createPlayerJob(playerId);
			
			for (String jobName : database.playerJobs(playerId)) {
				PlayerJobData playerJobData = new PlayerJobData(jobName);
				playerJobData.setLevel(database.jobIntData(playerId,
					jobName,
					"xp"));
				playerJobData.setLevel(database.jobIntData(playerId,
					jobName,
					"level"));
				
				playerJobData.setMaxExp(MathLevelsUtils.calculateNumber(configurationHandler.text("config.yml", "config.formula.max-xp"), 1));
				dataLoader.playerJob(playerId).putJob(jobName, playerJobData);
			}
		});
	}
}

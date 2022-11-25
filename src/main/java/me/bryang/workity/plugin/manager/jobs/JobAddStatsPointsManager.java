package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.data.jobs.JobData;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class JobAddStatsPointsManager
implements JobManager {
	private final Database database;
	private final DataModelLoader dataLoader;
	
	public JobAddStatsPointsManager(Database database, DataModelLoader dataLoader) {
		this.database = Objects.requireNonNull(database, "The Database object is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader instance is null.");
	}
	
	@Override
	public void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		JobData jobData = dataLoader.jobData().get(jobName);
		UUID playerId = player.getUniqueId();
		
		if (!jobData.isGlobalStatus()) {
			if (jobData.blockData(itemName).isDisableStatus()) return;
			
			int itemDataStats = database.jobIntData(playerId, jobName, "stats.item." + itemName);
			if (itemDataStats == -1) database.insertJobData(playerId, jobName, "stats.item." + itemName, 1);
			else database.insertJobData(playerId, jobName, "stats.item." + itemName, itemDataStats + 1);
			
			database.save();
			playerJobData.jobData().put(itemName, itemDataStats + 1);
			return;
		}
		
		int itemDataStats = database.intData(playerId, "stats");
		if (itemDataStats == -1) database.insertData(playerId, "stats", 1);
		else database.insertData(playerId, "stats", itemDataStats + 1);
		
		database.save();
		playerJobData.increaseGlobalStats();
	}
}

package me.bryang.workity.api.managers;

import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.manager.jobs.JobManager;
import org.bukkit.entity.Player;

public interface WorkModelManager {
	void registerAction(JobManager jobManager);
	
	void registerActions(JobManager... jobManagers);
	
	void executeActions(Player player, String jobName, String itemName, PlayerJobData playerJobData);
	
	void unregisterAll();
}

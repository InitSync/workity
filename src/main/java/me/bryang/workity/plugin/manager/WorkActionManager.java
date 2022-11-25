package me.bryang.workity.plugin.manager;

import me.bryang.workity.api.managers.WorkModelManager;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.manager.jobs.JobManager;
import org.bukkit.entity.Player;

import java.util.*;

public class WorkActionManager
implements WorkModelManager {
	private final Set<JobManager> managers;
	
	public WorkActionManager() {
		this.managers = new HashSet<>();
	}
	
	@Override
	public void registerAction(JobManager jobManager) {
		managers.add(jobManager);
	}
	
	@Override
	public void registerActions(JobManager... jobManagers) {
		managers.addAll(Arrays.asList(jobManagers));
	}
	
	@Override
	public void executeActions(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		managers.forEach(manager -> manager.doWorkAction(player, jobName, itemName, playerJobData));
	}
	
	@Override
	public void unregisterAll() {
		if (!managers.isEmpty()) managers.clear();
	}
}

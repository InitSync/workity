package me.bryang.workity.plugin.listeners;

import me.bryang.workity.api.events.JobExecuteEvent;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.api.managers.WorkModelManager;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.data.jobs.JobData;
import me.bryang.workity.plugin.job.JobAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class WorkExecutorListener implements Listener {
	private final DataModelLoader dataLoader;
	private final WorkModelManager workActionManager;
	private final Map<String, JobData> jobData;
	
	public WorkExecutorListener(
		 DataModelLoader dataLoader,
		 WorkModelManager workActionManager,
		 Map<String, JobData> jobData) {
		this.workActionManager = Objects.requireNonNull(workActionManager, "The WorkActionManager instance is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
		this.jobData = Objects.requireNonNull(jobData, "The Job data map is null.");
	}
	
	@EventHandler
	public void onWork(JobExecuteEvent event) {
		for (String jobName : dataLoader.jobData().keySet()) {
			JobAction action = event.jobAction();
			
			if (!jobData.get(jobName).isActivityType(action.type())) continue;
			
			String dataRequired;
			
			Entity entity = action.entity();
			if (entity != null) {
				dataRequired = entity.getType()
					.name()
					.toUpperCase();
			} else {
				dataRequired = action.item()
					.getType()
					.name()
					.toUpperCase();
			}
			
			if (jobData.get(jobName).blockJobData().get(dataRequired) == null) continue;
			
			UUID targetId = event.targetId();
			PlayerJobData playerJobData = dataLoader.playerJob(targetId).job(jobName);
			if (playerJobData == null) return;
			
			workActionManager.executeActions(Bukkit.getPlayer(targetId), playerJobData.getName(), jobName, playerJobData);
		}
	}
}





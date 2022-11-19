package me.bryang.workity.listeners;

import me.bryang.workity.action.JobAction;
import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.data.jobs.JobData;
import me.bryang.workity.events.JobExecuteEvent;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.manager.WorkActionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class WorkExecutorListener implements Listener {
	private final DataLoader dataLoader;
	private final WorkActionManager workActionManager;
	
	public WorkExecutorListener(DataLoader dataLoader, WorkActionManager workActionManager) {
		this.workActionManager = Objects.requireNonNull(workActionManager, "The WorkActionManager instance is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
	}
	
	@EventHandler
	public void onWork(JobExecuteEvent event) {
		for (String jobName : dataLoader.jobData().keySet()) {
			JobAction action = event.jobAction();
			
			Map<String, JobData> jobData = dataLoader.jobData();
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
			
			if (jobData.get(jobName)
				.blockJobData()
				.get(dataRequired) == null
			) {
				continue;
			}
			
			UUID targetId = event.targetId();
			PlayerJobData playerJobData = dataLoader.playerJob(targetId).job(jobName);
			if (playerJobData == null) return;
			
			workActionManager.doActions(Bukkit.getPlayer(targetId),
				playerJobData.getName(),
				dataRequired,
				playerJobData);
		}
	}
}





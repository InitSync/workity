package me.bryang.workity.manager;

import me.bryang.workity.PluginCore;
import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.manager.jobs.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WorkActionManager {
	private final List<JobManager> jobManagers;
	
	public WorkActionManager() {
		this.jobManagers = new ArrayList<>();
	}
	
	public void addActions(JobManager... newJobManagers) {
		jobManagers.addAll(Arrays.asList(newJobManagers));
	}
	
	public void doActions(
		Player player,
		String jobName,
		String itemName,
		PlayerJobData playerJobData
	) {
		jobManagers.forEach(jobManager -> {
			jobManager.doWorkAction(player,
				jobName,
				itemName,
				playerJobData);
		});
	}
}

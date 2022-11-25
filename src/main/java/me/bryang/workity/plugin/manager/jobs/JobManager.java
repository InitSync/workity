package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.plugin.data.PlayerJobData;
import org.bukkit.entity.Player;

public interface JobManager {
	void doWorkAction(
		Player player,
		String jobName,
		String itemName,
		PlayerJobData playerJobData
	);
}

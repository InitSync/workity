package me.bryang.workity.api.loaders;

import me.bryang.workity.plugin.data.PlayerData;
import me.bryang.workity.plugin.data.jobs.JobData;

import java.util.Map;
import java.util.UUID;

public interface DataModelLoader {
	void createPlayerJob(UUID uuid);
	
	PlayerData playerJob(UUID uuid);
	
	JobData job(String jobName);
	
	boolean exists(String jobName);
	
	Map<String, JobData> jobData();
}

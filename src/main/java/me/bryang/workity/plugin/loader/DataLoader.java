package me.bryang.workity.plugin.loader;

import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.plugin.data.PlayerData;
import me.bryang.workity.plugin.data.jobs.JobData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataLoader
implements DataModelLoader {
	private final Map<UUID, PlayerData> playerJobData;
	private final Map<String, JobData> jobData;
	
	public DataLoader() {
		this.playerJobData = new HashMap<>();
		this.jobData = new HashMap<>();
	}
	
	@Override
	public void createPlayerJob(UUID uuid) {
		playerJobData.put(uuid, new PlayerData());
	}
	
	@Override
	public PlayerData playerJob(UUID uuid) {
		return playerJobData.get(uuid);
	}
	
	@Override
	public JobData job(String jobName) {
		return jobData.get(jobName);
	}
	
	@Override
	public boolean exists(String jobName) {
		return jobData.get(jobName) != null;
	}
	
	@Override
	public Map<String, JobData> jobData() {
		return jobData;
	}
}

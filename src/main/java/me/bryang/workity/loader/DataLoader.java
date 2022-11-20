package me.bryang.workity.loader;

import me.bryang.workity.data.PlayerData;
import me.bryang.workity.data.jobs.JobData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataLoader {
	
	private final Map<UUID, PlayerData> playerJobData;
	private final Map<String, JobData> jobData;
	
	public DataLoader() {
		this.playerJobData = new HashMap<>();
		this.jobData = new HashMap<>();
	}
	
	public void createPlayerJob(UUID uuid) {
		playerJobData.put(uuid, new PlayerData());
	}
	
	public PlayerData playerJob(UUID uuid) {
		return playerJobData.get(uuid);
	}
	
	public JobData job(String jobName) {
		return jobData.get(jobName);
	}
	
	public boolean exists(String jobName) {
		return jobData.get(jobName) != null;
	}
	
	public Map<String, JobData> jobData() {
		return jobData;
	}
}

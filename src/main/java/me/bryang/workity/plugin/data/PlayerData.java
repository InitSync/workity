package me.bryang.workity.plugin.data;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
	private final Map<String, PlayerJobData> jobDataMap = new HashMap<>();
	
	public void addJob(String jobName) {
		jobDataMap.put(jobName, new PlayerJobData(jobName));
	}
	
	public void putJob(String jobName, PlayerJobData playerJobData) {
		jobDataMap.put(jobName, playerJobData);
	}
	
	public PlayerJobData job(String jobName) {
		return jobDataMap.get(jobName);
	}
	
	public void removeJob(String jobName) {
		jobDataMap.remove(jobName);
	}
	
	public boolean hasJob(String jobName) {
		return jobDataMap.containsKey(jobName);
	}
	
	public int getJobSize() {
		return jobDataMap.size();
	}
	
	public Map<String, PlayerJobData> jobData() {
		return jobDataMap;
	}
}

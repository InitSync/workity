package me.bryang.workity.data;

import java.util.HashMap;
import java.util.Map;

public class PlayerJobData {
	private final String jobName;
	private final Map<String, Integer> itemData = new HashMap<>();
	private int globalStats;
	private double expPoints;
	private int maxXP;
	private int level;
	private double multiplier;
	
	public PlayerJobData(String jobName) {
		this.jobName = jobName;
	}
	
	public String getName() {
		return jobName;
	}
	
	public void decreaseExpPoints() {
		expPoints--;
	}
	
	public double getExpPoints() {
		return expPoints;
	}
	
	public void increaseLevel() {
		level++;
	}
	
	public void decreaseLevel() {
		level--;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setExpPoints(double expPoints) {
		this.expPoints = expPoints;
	}
	
	public int getMaxExp() {
		return maxXP;
	}
	
	public void setMaxExp(int maxExp) {
		this.maxXP = maxExp;
	}
	
	public Map<String, Integer> jobData() {
		return itemData;
	}
	
	public void increaseGlobalStats() {
		globalStats++;
	}
	
	public int getGlobalStats() {
		return globalStats;
	}
	
	public void setGlobalStats(int globalStats) {
		this.globalStats = globalStats;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
}


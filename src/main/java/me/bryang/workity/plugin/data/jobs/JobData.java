package me.bryang.workity.plugin.data.jobs;

import me.bryang.workity.plugin.job.JobType;
import org.apache.commons.lang.Validate;

import java.util.Map;
import java.util.Objects;

public class JobData {
	private final String jobName;
	private final Map<String, BlockJobData> blockJobData;
	private final JobType activityType;
	private final boolean globalStatus;
	
	public JobData(String jobName, boolean globalStatus, JobType activityType, Map<String, BlockJobData> blockJobData) {
		this.jobName = Objects.requireNonNull(jobName, "The job name is null.");
		Validate.notEmpty(jobName, "The job name is empty.");
		
		this.globalStatus = globalStatus;
		this.activityType = Objects.requireNonNull(activityType, "The job type is null.");
		this.blockJobData = Objects.requireNonNull(blockJobData, "The jobData map is null.");
	}
	
	public boolean isActivityType(JobType activityType) {
		return this.activityType == activityType;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public Map<String, BlockJobData> blockJobData() {
		return blockJobData;
	}
	
	public BlockJobData blockData(String blockName) {
		return blockJobData.get(blockName);
	}
	
	public boolean isGlobalStatus() {
		return globalStatus;
	}
}

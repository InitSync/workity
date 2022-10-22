package me.bryang.labority.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerData {

    private final Map<String, JobData> jobList = new HashMap<>();

    public void addJob(String jobName){
        jobList.put(jobName, new JobData(jobName));
    }

    public JobData getJob(String jobName){
        return jobList.get(jobName);
    }

    public void removeJob(String jobName) {
        jobList.remove(jobName);
    }

    public boolean hasTheJob(String jobName){
        return jobList.containsKey(jobName);
    }

    public int getJobSize(){
        return jobList.size();
    }

    public Set<String> getJobsNames(){
        return jobList.keySet();
    }
}

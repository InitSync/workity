package me.bryang.workity.api.database;

import java.util.List;
import java.util.UUID;

public interface Database {
	Database insertJobData(UUID playerId, String jobName, String playerData, String newData);
	
	Database insertJobData(UUID playerId, String jobName, String playerData, int newData);
	
	void insertData(UUID playerId, String playerData, int newData);
	
	String jobData(UUID playerId, String jobName, String playerData);
	
	int jobIntData(UUID playerId, String jobName, String playerData);
	
	String data(UUID playerId, String playerData);
	
	int intData(UUID playerId, String playerData);
	
	List<String> playersList();
	
	List<String> playerJobs(UUID playerUniqueId);
	
	void save();
}

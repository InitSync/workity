package me.bryang.workity.database;

import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.enums.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerFilesDatabase
implements Database {
	private final BukkitConfigurationHandler configurationHandler;
	
	public PlayerFilesDatabase(BukkitConfigurationHandler configurationHandler){
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
	}
	
	@Override
	public Database insertJobData(UUID playerId, String jobName, String playerData, String newData) {
		configurationHandler.doSomething("players.yml",
			Action.WRITE,
			"players." + playerId + ".jobs. " + jobName + "." + playerData,
			newData);
		return this;
	}
	
	@Override
	public Database insertJobData(UUID playerId, String jobName, String playerData, int newData) {
		configurationHandler.doSomething("players.yml",
			Action.WRITE,
			"players." + playerId + ".jobs. " + jobName + "." + playerData,
			newData);
		return this;
	}
	
	@Override
	public void insertData(UUID playerId, String playerData, int newData) {
		configurationHandler.doSomething("players.yml",
			Action.WRITE,
			"players." + playerId + ".jobs. " + playerData,
			newData);
	}
	
	@Override
	public String jobData(UUID playerId, String jobName, String playerData) {
		return configurationHandler.text("players.yml",
			"players." +
			playerId +
			".jobs." +
			jobName +
			"." +
			playerData);
	}
	
	@Override
	public int jobIntData(UUID playerId, String jobName, String playerData) {
		return configurationHandler.number("players.yml",
			"players." +
			playerId +
			".jobs." +
			jobName +
			"." +
			playerData);
	}
	
	@Override
	public String data(UUID playerId, String playerData) {
		return configurationHandler.text("players.yml",
			"players." +
			playerId +
			"." +
			playerData);
	}
	
	@Override
	public int intData(UUID playerId, String playerData) {
		return configurationHandler.number("players.yml",
			"players." +
			playerId +
			"." +
			playerData);
	}
	
	@Override
	public List<String> playersList() {
		return new ArrayList<>(configurationHandler.configSection("players.yml", "players").getKeys(false));
	}
	
	@Override
	public List<String> playerJobs(UUID playerId) {
		return new ArrayList<>(configurationHandler.configSection("players.yml", "players." + playerId + ".jobs").getKeys(false));
	}
	
	@Override
	public void save() {
		configurationHandler.doSomething("players.yml",
			Action.SAVE,
			null,
			null);
	}
}

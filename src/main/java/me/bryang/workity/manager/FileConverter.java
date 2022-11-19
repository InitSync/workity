package me.bryang.workity.manager;

import me.bryang.workity.action.JobType;
import me.bryang.workity.data.jobs.BlockJobData;
import me.bryang.workity.data.jobs.JobData;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileConverter {
	private final BukkitConfigurationModel configurationManager;
	private final BukkitConfigurationHandler configurationHandler;
	private final Map<String, BlockJobData> blockJobData;
	
	public FileConverter(BukkitConfigurationModel configurationManager, BukkitConfigurationHandler configurationHandler) {
		this.configurationManager = Objects.requireNonNull(configurationManager, "The BukkitConfigurationModel object is null.");
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler instance is null.");
		this.blockJobData = new HashMap<>();
	}
	
	public JobData convert(String file) {
		Validate.notEmpty(file, "The file name is empty.");
		
		configurationManager.create("jobs", file);
		
		for (String keys : configurationHandler.configSection(file, "job.items").getKeys(false)) {
			blockJobData.put(keys, new BlockJobData(configurationHandler.number(file, "job.items." + keys + ".money"),
				configurationHandler.number(file, "job.items." + keys + ".xp"),
				configurationHandler.condition(file, "job.items." + keys + ".enable-status")));
		}
		
		return new JobData(configurationHandler.text(file, "job.name"),
			configurationHandler.condition(file, "job.global-status"),
			JobType.valueOf(configurationHandler.text(file, "job.type").toUpperCase()),
			blockJobData);
	}
}

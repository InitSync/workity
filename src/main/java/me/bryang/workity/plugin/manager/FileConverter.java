package me.bryang.workity.plugin.manager;

import me.bryang.workity.api.managers.FileModelConverter;
import me.bryang.workity.plugin.data.jobs.BlockJobData;
import me.bryang.workity.plugin.data.jobs.JobData;
import me.bryang.workity.plugin.job.JobType;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileConverter
implements FileModelConverter {
	private final BukkitConfigurationModel configurationManager;
	private final BukkitConfigurationHandler configurationHandler;
	private final Map<String, BlockJobData> blockJobData;
	
	public FileConverter(BukkitConfigurationModel configurationManager, BukkitConfigurationHandler configurationHandler) {
		this.configurationManager = Objects.requireNonNull(configurationManager, "The BukkitConfigurationModel object is null.");
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler instance is null.");
		this.blockJobData = new HashMap<>();
	}
	
	@Override
	public JobData convert(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		configurationManager.create("jobs", fileName);
		
		for (String keys : configurationHandler.configSection(fileName, "job.items").getKeys(false)) {
			blockJobData.put(
				keys,
				new BlockJobData(
					configurationHandler.number(fileName, "job.items." + keys + ".money"),
					configurationHandler.number(fileName, "job.items." + keys + ".xp"),
					configurationHandler.condition(fileName, "job.items." + keys + ".enable-status")
				)
			);
		}
		
		return new JobData(
			configurationHandler.text(fileName, "job.name"),
			configurationHandler.condition(fileName, "job.global-status"),
			JobType.valueOf(configurationHandler.text(fileName, "job.type").toUpperCase()),
			blockJobData
		);
	}
}

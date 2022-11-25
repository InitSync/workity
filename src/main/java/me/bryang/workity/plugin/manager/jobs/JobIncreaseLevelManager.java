package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.api.database.Database;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.utils.MathLevelsUtils;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class JobIncreaseLevelManager
implements JobManager {
	private final BukkitConfigurationHandler configurationHandler;
	private final Database database;
	
	public JobIncreaseLevelManager(BukkitConfigurationHandler configurationHandler, Database database) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is empty.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
	}
	
	@Override
	public void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		int maxExperience = playerJobData.getMaxExp();
		if (maxExperience >= playerJobData.getExpPoints()) return;
		
		int level = playerJobData.getLevel();
		if (level == configurationHandler.number("config.yml", "config.max-level-jobs")) {
			playerJobData.setExpPoints(maxExperience);
			player.sendMessage(configurationHandler.text("messages.yml", "error.max-level"));
			return;
		}
		
		playerJobData.setLevel(level + 1);
		playerJobData.setExpPoints(0);
		playerJobData.setMaxExp(MathLevelsUtils.calculateNumber(configurationHandler.text("config.yml", "config.formula.max-xp"), level));
		
		UUID playerId = player.getUniqueId();
		database.insertJobData(playerId, jobName, "level", playerJobData.getLevel() + 1)
			.insertJobData(playerId, jobName, "xp", 0)
			.save();
		
		player.sendMessage(configurationHandler.text("messages.yml", "jobs.gain.level")
			.replace("%new_level%", Integer.toString(level)));
	}
}

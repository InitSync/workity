package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.api.managers.ActionModelManager;
import me.bryang.workity.plugin.data.PlayerJobData;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JobActionRewardsManager
implements JobManager {
	private final BukkitConfigurationHandler configurationHandler;
	private final ActionModelManager actionManager;
	
	public JobActionRewardsManager(BukkitConfigurationHandler configurationHandler, ActionModelManager actionManager) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler object is null.");
		this.actionManager = Objects.requireNonNull(actionManager, "The ActionControllerModel object is null.");
	}
	
	@Override
	public void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		if (!configurationHandler.condition("config.yml", "config.rewards.enabled")) return;
		
		int level = playerJobData.getLevel();
		
		if (configurationHandler.configSection("config.yml", "config.rewards." + level) != null) return;
		
		actionManager.executeActions(player, configurationHandler.textList("config.yml", "config.rewards.levels." + level + ".actions"));
	}
}

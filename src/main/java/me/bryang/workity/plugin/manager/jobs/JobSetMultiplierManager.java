package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.manager.VaultHookManager;
import net.milkbowl.vault.permission.Permission;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JobSetMultiplierManager
implements JobManager {
	private final BukkitConfigurationHandler configurationHandler;
	
	private final VaultHookManager vaultHookManager;
	
	public JobSetMultiplierManager(BukkitConfigurationHandler configurationHandler, VaultHookManager vaultHookManager) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler object is null.");
		this.vaultHookManager = Objects.requireNonNull(vaultHookManager, "The VaultHookManager instance is null.");
	}
	
	@Override
	public void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		Permission permission = vaultHookManager.getPermission();
		double multiplier;
		
		if (permission.hasGroupSupport()) {
			String group = permission.getPrimaryGroup(player);
			if (configurationHandler.configSection("config.yml", "config.multiplier.group." + group) == null) {
				multiplier = configurationHandler.number("config.yml", "config.multiplier.default");
			} else {
				multiplier = configurationHandler.number("config.yml", "config.multiplier.group." + group);
			}
		} else multiplier = configurationHandler.number("config.yml", "config.multiplier.default");
		
		playerJobData.setMultiplier(multiplier);
	}
}

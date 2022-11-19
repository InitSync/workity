package me.bryang.workity.manager.jobs;

import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.manager.VaultHookManager;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JobActionRewardsManager
implements JobManager {
	private final BukkitConfigurationHandler configurationHandler;
	private final VaultHookManager vaultHookManager;
	
	public JobActionRewardsManager(BukkitConfigurationHandler configurationHandler, VaultHookManager vaultHookManager) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler object is null.");
		this.vaultHookManager = Objects.requireNonNull(vaultHookManager, "The VaultHookManager object is null.");
	}
	
	@Override
	public void doWorkAction(
		Player player,
		String jobName,
		String itemName,
		PlayerJobData playerJobData
	) {
		if (!configurationHandler.condition("config.yml", "config.rewards.enabled")) return;
		
		int level = playerJobData.getLevel();
		
		if (configurationHandler.configSection("config.yml", "config.rewards." + level) != null) return;
		
		configurationHandler.textList("config.yml", "config.rewards." + level + ".format")
			.forEach(format -> {
				if (format.startsWith("[BROADCAST]")) {
					Bukkit.broadcastMessage(TextUtils.colorize(format.substring(11)
						.replace("%player%", player.getName())));
				}
				
				if (format.startsWith("[COMMAND]")) {
					player.performCommand(format.substring(9).replace("%player%", player.getName()));
				}
				
				if (format.startsWith("[MONEY]")) {
					vaultHookManager.getEconomy().depositPlayer(player, Double.parseDouble(format.substring(7)));
				}
			});
	}
}

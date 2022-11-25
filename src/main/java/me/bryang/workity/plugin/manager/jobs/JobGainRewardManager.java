package me.bryang.workity.plugin.manager.jobs;

import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.data.jobs.BlockJobData;
import me.bryang.workity.plugin.manager.VaultHookManager;
import me.bryang.workity.plugin.utils.MathLevelsUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JobGainRewardManager
implements JobManager {
	private final DataModelLoader dataLoader;
	private final BukkitConfigurationHandler configurationHandler;
	private final Database database;
	private final VaultHookManager vaultHookManager;
	
	public JobGainRewardManager(
		 DataModelLoader dataLoader,
		 BukkitConfigurationHandler configurationHandler,
		 Database database,
		 VaultHookManager vaultHookManager) {
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader is null.");
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
		this.vaultHookManager = Objects.requireNonNull(vaultHookManager, "The VaultHookManager instance is null.");
	}
	
	@Override
	public void doWorkAction(Player player, String jobName, String itemName, PlayerJobData playerJobData) {
		BlockJobData blockJobData = dataLoader.job(jobName).blockData(itemName);
		
		double multiplier = playerJobData.getMultiplier();
		
		int level = playerJobData.getLevel();
		double moneyReward = MathLevelsUtils.calculateDoubleNumber(configurationHandler.text("config.yml", "config.formula.gain-money")
			.replace("%money%", Integer.toString(blockJobData.getGainMoney())), level) * multiplier;
		double xpReward = MathLevelsUtils.calculateNumber(configurationHandler.text("config.yml", "config.formula.gain-xp")
			.replace("%xp%", Integer.toString(blockJobData.getGainXP())), level) * (int) multiplier;
		
		vaultHookManager.getEconomy().depositPlayer(player, moneyReward);
		
		double experiencePoints = playerJobData.getExpPoints();
		playerJobData.setExpPoints(experiencePoints + xpReward);
		
		player.spigot().sendMessage(
			ChatMessageType.ACTION_BAR,
			new TextComponent(configurationHandler.text("config.yml", "config.action-bar.gain-rewards")
				 .replace("%money%", Double.toString(moneyReward))
				 .replace("%xp%", Double.toString(xpReward))
			)
		);
		
		database.insertJobData(player.getUniqueId(), jobName, "xp", (int) (experiencePoints + xpReward)).save();
	}
}

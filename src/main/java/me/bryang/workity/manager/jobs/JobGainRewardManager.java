package me.bryang.workity.manager.jobs;

import me.bryang.workity.data.PlayerJobData;
import me.bryang.workity.data.jobs.BlockJobData;
import me.bryang.workity.database.Database;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.manager.VaultHookManager;
import me.bryang.workity.utils.MathLevelsUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.entity.Player;
import sun.misc.FloatingDecimal;

import java.util.Objects;

public class JobGainRewardManager
implements JobManager {
	private final DataLoader dataLoader;
	private final BukkitConfigurationHandler configurationHandler;
	private final Database database;
	private final VaultHookManager vaultHookManager;
	
	public JobGainRewardManager(
		DataLoader dataLoader,
		BukkitConfigurationHandler configurationHandler,
		Database database,
		VaultHookManager vaultHookManager
	) {
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader is null.");
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
		this.vaultHookManager = Objects.requireNonNull(vaultHookManager, "The VaultHookManager instance is null.");
	}
	
	@Override
	public void doWorkAction(
		Player player,
		String jobName,
		String itemName,
		PlayerJobData playerJobData
	) {
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
		
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
			new TextComponent(
				configurationHandler.text("config.yml", "config.action-bar.gain-rewards")
					.replace("%money%", FloatingDecimal.toJavaFormatString(moneyReward))
					.replace("%xp%", FloatingDecimal.toJavaFormatString(xpReward))));
		
		database.insertJobData(player.getUniqueId(),
			jobName,
			"xp",
			(int) (experiencePoints + xpReward))
			.save();
	}
}

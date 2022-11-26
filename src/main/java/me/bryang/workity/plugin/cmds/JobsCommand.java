package me.bryang.workity.plugin.cmds;

import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.plugin.data.PlayerData;
import me.bryang.workity.plugin.data.PlayerJobData;
import me.bryang.workity.plugin.enums.Permission;
import me.bryang.workity.plugin.utils.MathLevelsUtils;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class JobsCommand
implements CommandExecutor {
	private final BukkitConfigurationHandler configurationHandler;
	private final DataModelLoader dataLoader;
	private final Database database;
	
	public JobsCommand(
		BukkitConfigurationHandler configurationHandler,
		DataModelLoader dataLoader,
		Database database
	) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler object is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
		this.database = Objects.requireNonNull(database, "The Database object is null.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player player)) return false;
		
		String prefix = configurationHandler.text("config.yml", "config.prefix");
		
		if (!player.hasPermission(Permission.JOBS_CMD.getPermission())) {
			player.sendMessage(configurationHandler.text("messages.yml", "error.no-permission")
				.replace("<prefix>", prefix));
			return false;
		}
		
		if (args.length == 0) {
			configurationHandler.textList("messages.yml", "jobs.help.format").forEach(player::sendMessage);
			return false;
		}
		
		switch (args[0]) {
			default -> {
				player.sendMessage(configurationHandler.text("messages.yml", "error.no-argument")
					.replace("<prefix>", prefix));
			}
			case "stats" -> {
				PlayerData playerData = dataLoader.playerJob(player.getUniqueId());
				if (playerData.jobData().isEmpty()) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.dont-join-any-jobs"));
					break;
				}
				
				playerData.jobData()
					.values()
					.forEach(jobData -> {
					
					});
				configurationHandler.textList("messages.yml", "jobs.stats.message");
			}
			case "setlevel" -> {
				if (args.length == 1) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.no-argument")
						.replace("<prefix>", prefix));
					break;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.no-online")
						.replace("<prefix>", prefix));
					break;
				}
				
				if (args.length == 2) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.no-argument")
						.replace("<prefix>", prefix));
					break;
				}
				
				String job = args[2];
				if (!dataLoader.exists(job)) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.unknown-job")
						.replace("<prefix>", prefix)
						.replace("%jobs%", configurationHandler.text("config.yml", "jobs." + job + ".name")));
					break;
				}
				
				int level;
				try { level = Integer.parseInt(args[3]); }
				catch (NumberFormatException exception) {
					exception.printStackTrace();
					return false;
				}
				
				if (level < 0) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.negative-number")
						.replace("<prefix>", prefix));
					break;
				}
				
				UUID targetId = target.getUniqueId();
				PlayerData data = dataLoader.playerJob(targetId);
				if (data.job(job) == null) data.addJob(job);
				
				PlayerJobData jobData = data.job(job);
				jobData.setLevel(level);
				jobData.setMaxExp(MathLevelsUtils.calculateNumber(configurationHandler.text("config.yml", "config.formula.max-xp"), level));
				
				database.insertJobData(targetId, job, "level", jobData.getLevel())
					.insertJobData(targetId, job, "exp", 0)
					.save();
			}
		}
		return false;
	}
}

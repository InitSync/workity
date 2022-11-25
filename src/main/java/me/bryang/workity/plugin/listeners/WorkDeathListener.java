package me.bryang.workity.plugin.listeners;

import me.bryang.workity.api.events.JobExecuteEvent;
import me.bryang.workity.plugin.job.JobAction;
import me.bryang.workity.plugin.job.JobType;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class WorkDeathListener implements Listener {
	
	private final BukkitConfigurationHandler configurationHandler;
	
	public WorkDeathListener(BukkitConfigurationHandler configurationHandler) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
	}
	
	@EventHandler
	public void playerKillMob(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player player)) return;
		
		if (player.getGameMode() == GameMode.CREATIVE && configurationHandler.condition("config.yml", "config.disable-creative")) return;
		
		Bukkit.getPluginManager().callEvent(
			new JobExecuteEvent(player.getUniqueId(),
			new JobAction(JobType.PLAYER_KILL_ENTITY, event.getEntity())
		));
	}
}

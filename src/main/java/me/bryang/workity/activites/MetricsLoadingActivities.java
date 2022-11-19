package me.bryang.workity.activites;

import me.bryang.workity.PluginMetrics;
import me.bryang.workity.Workity;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;

import java.util.Objects;

public class MetricsLoadingActivities implements Activities {
	private final BukkitConfigurationHandler configurationHandler;
	
	public MetricsLoadingActivities(BukkitConfigurationHandler configurationHandler) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler instance is null.");
	}
	
	public void loadTask() {
		if (configurationHandler.condition("config.yml", "enabled-metrics")) new PluginMetrics(Workity.instance(), 16726);
	}
}

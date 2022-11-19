package me.bryang.workity.services;

import me.bryang.workity.PluginCore;
import me.bryang.workity.Workity;
import me.bryang.workity.manager.ActivitiesManager;
import me.bryang.workity.manager.FileConverter;
import me.bryang.workity.manager.VaultHookManager;
import me.bryang.workity.manager.WorkActionManager;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;

import java.util.logging.Logger;

public interface ManagerService {
	static ActivitiesManager activitiesManager() {
		return new ActivitiesManager();
	}
	
	static VaultHookManager vaultHookManager(Workity workity, Logger logger) {
		return new VaultHookManager(workity, logger);
	}
	
	static WorkActionManager workActionManager(PluginCore core) {
		return new WorkActionManager(core);
	}
	
	static FileConverter fileConverter(BukkitConfigurationModel configurationManager, BukkitConfigurationHandler configurationHandler) {
		return new FileConverter(configurationManager, configurationHandler);
	}
}

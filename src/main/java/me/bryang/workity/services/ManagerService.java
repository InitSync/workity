package me.bryang.workity.services;

import me.bryang.workity.Workity;
import me.bryang.workity.manager.ActivitiesManager;
import me.bryang.workity.manager.FileConverter;
import me.bryang.workity.manager.VaultHookManager;
import me.bryang.workity.manager.WorkActionManager;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;

public interface ManagerService {
	static ActivitiesManager activitiesManager() {
		return new ActivitiesManager();
	}
	
	static VaultHookManager vaultHookManager(Workity workity) {
		return new VaultHookManager(workity);
	}
	
	static WorkActionManager workActionManager() {
		return new WorkActionManager();
	}
	
	static FileConverter fileConverter(BukkitConfigurationModel configurationManager, BukkitConfigurationHandler configurationHandler) {
		return new FileConverter(configurationManager, configurationHandler);
	}
}

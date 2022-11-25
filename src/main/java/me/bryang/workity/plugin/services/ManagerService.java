package me.bryang.workity.plugin.services;

import me.bryang.workity.Workity;
import me.bryang.workity.plugin.manager.*;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.bukkit.plugin.PluginManager;

public interface ManagerService {
	static ActivityManager activitiesManager() {
		return new ActivityManager();
	}
	
	static VaultHookManager vaultHookManager() {
		return new VaultHookManager();
	}
	
	static WorkActionManager workActionManager() {
		return new WorkActionManager();
	}
	
	static FileConverter fileConverter(BukkitConfigurationModel configurationManager, BukkitConfigurationHandler configurationHandler) {
		return new FileConverter(configurationManager, configurationHandler);
	}
	
	static ActionManager actionManager(Workity plugin) {
		return new ActionManager(plugin);
	}
}

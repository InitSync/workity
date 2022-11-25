package me.bryang.workity.plugin.services;

import me.bryang.workity.plugin.storage.PlayerFilesDatabase;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;

public interface DatabaseService {
	static PlayerFilesDatabase playerFilesDatabase(BukkitConfigurationHandler configurationHandler) {
		return new PlayerFilesDatabase(configurationHandler);
	}
}

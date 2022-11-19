package me.bryang.workity.services;

import me.bryang.workity.database.PlayerFilesDatabase;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;

public interface DatabaseService {
	static PlayerFilesDatabase playerFilesDatabase(BukkitConfigurationHandler configurationHandler) {
		return new PlayerFilesDatabase(configurationHandler);
	}
}

package me.bryang.workity.api.models;

import me.bryang.workity.api.enums.Action;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface ExecutableModel {
	Action type();
	
	void execute(JavaPlugin plugin, Player player, String container);
}

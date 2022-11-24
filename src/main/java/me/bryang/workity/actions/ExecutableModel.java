package me.bryang.workity.actions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface ExecutableModel {
	Type actionType();
	
	void execute(JavaPlugin plugin, Player player, String container);
	
	enum Type {
		SOUND,
		POTION_EFFECT,
		TITLE,
		ACTION_BAR,
		COMMAND,
		BROADCAST,
		MONEY,
		CONNECT,
		MESSAGE
	}
}

package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandType
implements ExecutableModel {
	public CommandType() {}
	
	@Override
	public Action type() {
		return Action.COMMAND;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		String[] parts = container.split(";", 2);
		
		String commandToExecute = parts[1];
		
		if (Boolean.parseBoolean(parts[0])) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToExecute);
			return;
		}
		
		player.performCommand(commandToExecute);
	}
}

package me.bryang.workity.api;

import me.bryang.workity.actions.ExecutableModel;
import org.bukkit.entity.Player;

import java.util.List;

public interface ActionControllerModel {
	void registerAction(ExecutableModel executableModel);
	
	void registerActions(ExecutableModel... executableModels);
	
	void executeActions(Player player, List<String> container);
	
	void unregister();
}

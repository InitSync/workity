package me.bryang.workity.api.managers;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import org.bukkit.entity.Player;

import java.util.List;

public interface ActionModelManager {
	default void registerAction(ExecutableModel executableModel) {
		registerAction(executableModel.type(), executableModel);
	}
	
	void registerAction(Action type, ExecutableModel executableModel);
	
	void registerActions(ExecutableModel... executableModels);
	
	void executeActions(Player player, List<String> container);
	
	void unregister();
}

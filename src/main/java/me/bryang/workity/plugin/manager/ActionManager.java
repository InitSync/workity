package me.bryang.workity.plugin.manager;

import me.bryang.workity.Workity;
import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.events.ActionListExecuteEvent;
import me.bryang.workity.api.managers.ActionModelManager;
import me.bryang.workity.api.models.ExecutableModel;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ActionManager
implements ActionModelManager {
	private final Workity plugin;
	private final Map<Action, ExecutableModel> actions;
	
	public ActionManager(Workity plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Workity instance is null.");
		this.actions = new HashMap<>();
	}
	
	@Override
	public void registerAction(Action type, ExecutableModel executableModel) {
		actions.put(type, executableModel);
	}
	
	@Override
	public void registerActions(ExecutableModel... executableModels) {
		for (ExecutableModel executableModel : executableModels) {
			actions.put(executableModel.type(), executableModel);
		}
	}
	
	@Override
	public void executeActions(Player player, List<String> containers) {
		ActionListExecuteEvent executeEvent = new ActionListExecuteEvent(player, containers);
		Bukkit.getPluginManager().callEvent(executeEvent);
		if (!executeEvent.isCancelled()) {
			containers.forEach(container -> {
				actions.get(Action.valueOf(StringUtils.substringBetween(container, "[", "]").toUpperCase()))
					.execute(plugin, player, container.contains(" ")
						? container.split(" ", 2)[1]
						: "");
			});
		}
	}
	
	@Override
	public void unregister() {
		actions.clear();
	}
}

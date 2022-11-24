package me.bryang.workity.manager;

import me.bryang.workity.Workity;
import me.bryang.workity.actions.ExecutableModel;
import me.bryang.workity.api.ActionControllerModel;
import me.bryang.workity.api.events.ActionListExecuteEvent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ActionManager
implements ActionControllerModel {
	private final Workity plugin;
	private final Map<ExecutableModel.Type, ExecutableModel> actions;
	
	public ActionManager(Workity plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Workity instance is null.");
		this.actions = new HashMap<>();
	}
	
	@Override
	public void registerAction(ExecutableModel executableModel) {
		actions.put(executableModel.actionType(), executableModel);
	}
	
	@Override
	public void registerActions(ExecutableModel... executableModels) {
		Arrays.asList(executableModels).forEach(this::registerAction);
	}
	
	@Override
	public void executeActions(Player player, List<String> containers) {
		ActionListExecuteEvent executeEvent = new ActionListExecuteEvent(player, containers);
		Bukkit.getPluginManager().callEvent(executeEvent);
		if (!executeEvent.isCancelled()) {
			containers.forEach(container -> {
				actions.get(ExecutableModel.Type.valueOf(StringUtils.substringBetween(container, "[", "]").toUpperCase()))
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

package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastType
implements ExecutableModel {
	public BroadcastType() {}
	
	@Override
	public Action type() {
		return Action.BROADCAST;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		Bukkit.getOnlinePlayers().forEach(connected -> connected.sendMessage(TextUtils.colorize(container)));
	}
}

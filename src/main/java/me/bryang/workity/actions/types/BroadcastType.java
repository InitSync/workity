package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastType
implements ExecutableModel {
	public BroadcastType() {}
	
	@Override
	public Type actionType() {
		return Type.BROADCAST;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		Bukkit.getOnlinePlayers().forEach(connected -> connected.sendMessage(TextUtils.colorize(container)));
	}
}

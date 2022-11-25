package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageType
implements ExecutableModel {
	public MessageType() {}
	
	@Override
	public Action type() {
		return Action.MESSAGE;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		player.sendMessage(TextUtils.colorize(container));
	}
}

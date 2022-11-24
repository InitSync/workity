package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageType
implements ExecutableModel {
	public MessageType() {}
	
	@Override
	public Type actionType() {
		return Type.MESSAGE;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		player.sendMessage(TextUtils.colorize(container));
	}
}

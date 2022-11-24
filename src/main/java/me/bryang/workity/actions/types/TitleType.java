package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import me.bryang.workity.utils.LogUtils;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TitleType
implements ExecutableModel {
	public TitleType() {}
	
	@Override
	public Type actionType() {
		return Type.TITLE;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		String[] parts = container.split(";", 5);
		
		int fadeIn;
		int stay;
		int fadeOut;
		try {
			fadeIn = Integer.parseInt(parts[2]);
			stay = Integer.parseInt(parts[3]);
			fadeOut = Integer.parseInt(parts[4]);
		} catch (NumberFormatException exception) {
			LogUtils.error("Cannot parse the title time parameters.");
			exception.printStackTrace();
			return;
		}
		
		player.sendTitle(
			TextUtils.colorize(parts[0]),
			TextUtils.colorize(parts[1]),
			fadeIn,
			stay,
			fadeOut
		);
	}
}

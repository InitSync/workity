package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import me.bryang.workity.plugin.utils.LogUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SoundType
implements ExecutableModel {
	public SoundType() {}
	
	@Override
	public Action type() {
		return Action.SOUND;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		String[] parts = container.split(";", 3);
		
		int volume;
		int pitch;
		try {
			volume = Integer.parseInt(parts[1]);
			pitch = Integer.parseInt(parts[2]);
		} catch (NumberFormatException exception) {
			LogUtils.error("Cannot parse the sound parameters.");
			exception.printStackTrace();
			return;
		}
		
		player.playSound(player.getLocation(), Sound.valueOf(parts[0]), volume, pitch);
	}
}

package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import me.bryang.workity.utils.LogUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionType
implements ExecutableModel {
	public PotionType() {}
	
	@Override
	public Type actionType() {
		return Type.POTION_EFFECT;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		String[] parts = container.split(";", 3);
		
		int duration;
		int amplifier;
		try {
			duration = Integer.parseInt(parts[1]);
			amplifier = Integer.parseInt(parts[2]);
		} catch (NumberFormatException exception) {
			LogUtils.error("Cannot parse the potion effect parameters.");
			exception.printStackTrace();
			return;
		}
		
		PotionEffectType effectType = PotionEffectType.getByName(parts[0].toUpperCase());
		if (effectType == null) {
			LogUtils.error("The potion effect specified is null, check the name.");
			return;
		}
		
		player.addPotionEffect(
			new PotionEffect(
				effectType,
				duration,
				amplifier,
				true,
				true,
				true)
		);
	}
}

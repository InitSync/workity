package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarType
implements ExecutableModel {
	public ActionBarType() {}
	
	@Override
	public Type actionType() {
		return Type.ACTION_BAR;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		String[] parts = container.split(";", 2);
		
		new BukkitRunnable() {
			int duration = Integer.parseInt(parts[1]);
			
			@Override
			public void run() {
				if (duration <= 0) cancel();
				
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(TextUtils.colorize(parts[0])));
				duration--;
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 20L);
	}
}

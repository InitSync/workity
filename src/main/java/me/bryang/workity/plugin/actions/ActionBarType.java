package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
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
	public Action type() {
		return Action.ACTION_BAR;
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

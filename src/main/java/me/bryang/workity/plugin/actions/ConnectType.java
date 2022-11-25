package me.bryang.workity.plugin.actions;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConnectType
implements ExecutableModel {
	public ConnectType() {}
	
	@Override
	public Action type() {
		return Action.CONNECT;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		if (!Bukkit.getMessenger().isOutgoingChannelRegistered(plugin, "BungeeCord")) return;
		
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeUTF("ConnectOther");
		output.writeUTF(player.getName());
		output.writeUTF(container);
		
		player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
	}
}

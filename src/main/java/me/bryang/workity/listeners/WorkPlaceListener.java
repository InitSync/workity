package me.bryang.workity.listeners;

import me.bryang.workity.Workity;
import me.bryang.workity.action.JobAction;
import me.bryang.workity.action.JobType;
import me.bryang.workity.events.JobExecuteEvent;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public class WorkPlaceListener implements Listener {
	private final BukkitConfigurationHandler configurationHandler;
	
	public WorkPlaceListener(BukkitConfigurationHandler configurationHandler) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
	}
	
	@EventHandler
	public void onBreak(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() == GameMode.CREATIVE && configurationHandler.condition("config.yml", "config.disable-creative")) return;
		
		Block block = event.getBlock();
		if (block.getType()
			.name()
			.toLowerCase()
			.contains("ore")
		) {
			block.setMetadata("workity:block-placed", new FixedMetadataValue(Workity.instance(), true));
		}
		
		Bukkit.getPluginManager().callEvent(new JobExecuteEvent(player.getUniqueId(),
			new JobAction(JobType.PLAYER_PLACE_BLOCK,
				new ItemStack(block.getType()))));
	}
}

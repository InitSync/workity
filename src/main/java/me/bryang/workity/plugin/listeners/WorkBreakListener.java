package me.bryang.workity.plugin.listeners;

import me.bryang.workity.api.events.JobExecuteEvent;
import me.bryang.workity.plugin.job.JobAction;
import me.bryang.workity.plugin.job.JobType;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import java.util.Objects;

public class WorkBreakListener
implements Listener{
	
	private final BukkitConfigurationHandler configurationHandler;
	
	public WorkBreakListener(BukkitConfigurationHandler configurationHandler) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler is null.");
	}
	
	@EventHandler
	public void onBreakJob(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() == GameMode.CREATIVE && configurationHandler.condition("config.yml", "config.disable-creative")) return;
		
		ItemStack item = player.getInventory().getItemInMainHand();
		Material material = item.getType();
		if (material != Material.AIR && material.name()
			 .toLowerCase()
			 .contains("pickaxe") && item.getEnchantments().containsKey(Enchantment.SILK_TOUCH)
			 && configurationHandler.condition("config.yml", "config.disable-silk-touch")) {
			return;
		}
		
		Block block = event.getBlock();
		if (block.hasMetadata("workity:block-placed")) return;
		if (block.hasMetadata("workity:furnace")) return;
		
		Bukkit.getPluginManager().callEvent(new JobExecuteEvent(
			 player.getUniqueId(),
			 new JobAction(JobType.PLAYER_BREAK_BLOCK, new ItemStack(block.getType())))
		);
	}
}

package me.bryangaming.labority.listener;

import me.bryangaming.labority.action.Action;
import me.bryangaming.labority.action.JobType;
import me.bryangaming.labority.event.JobsEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){

        Block block = event.getBlock();

        Bukkit.getPluginManager().callEvent(new JobsEvent(event.getPlayer().getUniqueId(),
                new Action(JobType.PLAYER_BREAK_BLOCK, new ItemStack(block.getType()))));

    }
}

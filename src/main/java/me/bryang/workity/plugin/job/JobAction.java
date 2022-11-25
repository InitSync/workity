package me.bryang.workity.plugin.job;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class JobAction {
	private final JobType jobType;
	private final Entity entity;
	private final ItemStack itemStack;
	
	public JobAction(JobType jobType, Entity entity) {
		this.jobType = jobType;
		this.entity = entity;
		this.itemStack = null;
	}
	
	public JobAction(JobType jobType, ItemStack itemStack) {
		this.jobType = jobType;
		this.itemStack = itemStack;
		this.entity = null;
	}
	
	public JobType type() {
		return jobType;
	}
	
	public Entity entity() {
		return entity;
	}
	
	public ItemStack item() {
		return itemStack;
	}
}

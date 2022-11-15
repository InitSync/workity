package me.bryang.workity.action;

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

    public JobType getType() {
        return jobType;
    }

    public Entity getEntity() {
        return entity;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}

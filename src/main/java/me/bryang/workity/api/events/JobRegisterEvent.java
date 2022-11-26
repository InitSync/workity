package me.bryang.workity.api.events;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public class JobRegisterEvent
extends Event
implements Cancellable {
	private final HandlerList handlers = new HandlerList();
	private final Player player;
	private final String jobName;
	
	private boolean cancelled;
	
	public JobRegisterEvent(Player player, String jobName) {
		this.player = Objects.requireNonNull(player, "The player is null.");
		this.jobName = Objects.requireNonNull(jobName, "The job name is null.");
		Validate.notEmpty(jobName, "The job name is empty.");
	}
	
	public Player player() {
		return player;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}

package me.bryang.workity.events;

import me.bryang.workity.action.JobAction;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;
import java.util.UUID;

public class JobExecuteEvent extends Event {
	private final HandlerList handlerList = new HandlerList();
	private final UUID targetId;
	private final JobAction jobAction;
	
	public JobExecuteEvent(UUID targetId, JobAction jobAction) {
		this.jobAction = Objects.requireNonNull(jobAction, "The job action is null.");
		this.targetId = Objects.requireNonNull(targetId, "The target uuid is null.");
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public UUID targetId() {
		return targetId;
	}
	
	public JobAction jobAction() {
		return jobAction;
	}
}

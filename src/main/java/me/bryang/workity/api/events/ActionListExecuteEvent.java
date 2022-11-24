package me.bryang.workity.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;
import java.util.Objects;

public class ActionListExecuteEvent
extends Event
implements Cancellable {
	private final HandlerList handlers = new HandlerList();
	private final Player player;
	private final List<String> types;
	
	private boolean cancelled;
	
	public ActionListExecuteEvent(Player player, List<String> types) {
		this.player = Objects.requireNonNull(player, "The player is null.");
		this.types = Objects.requireNonNull(types, "The actions list is null.");
	}
	
	public Player player() {
		return player;
	}
	
	public List<String> types() {
		return types;
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

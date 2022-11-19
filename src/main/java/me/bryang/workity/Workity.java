package me.bryang.workity;

import org.bukkit.plugin.java.JavaPlugin;

public final class Workity
extends JavaPlugin {
	private static Workity instance;
	
	private PluginCore core;
	
	public Workity() {
		instance = this;
		
		core = new PluginCore(this);
	}
	
	public static Workity instance() {
		if (instance == null) throw new IllegalStateException("The Workity instance is null.");
		
		return instance;
	}
	
	@Override
	public void onEnable() {
		core.enable();
		
		getLogger().info(" Created by " + getDescription().getAuthors().get(0));
		getLogger().info(" You are using the version " + getDescription().getVersion() + ".");
		getLogger().info("Click to support: http://discord.devblook.team/");
		
	}
	
	@Override
	public void onDisable() {
		if (core != null) {
			core.disable();
			core = null;
		}
		
		if (instance != null) instance = null;
	}
}

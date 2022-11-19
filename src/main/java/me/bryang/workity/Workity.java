package me.bryang.workity;

import me.bryang.workity.utils.LogUtils;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class Workity
extends JavaPlugin {
	private final PluginDescriptionFile descriptionFile = getDescription();
	
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
		
		LogUtils.info(" Created by " + descriptionFile.getAuthors().get(0));
		LogUtils.info(" You are using the version " + descriptionFile.getVersion() + ".");
		LogUtils.info("Click to support: http://discord.devblook.team/");
		
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

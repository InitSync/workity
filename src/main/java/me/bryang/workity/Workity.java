package me.bryang.workity;

import me.bryang.workity.plugin.Loader;
import me.bryang.workity.plugin.services.LoaderService;
import me.bryang.workity.plugin.utils.LogUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Workity
extends JavaPlugin {
	public final String release = getDescription().getVersion();
	
	private static Workity instance;
	
	private Loader core;
	
	@Override
	public void onLoad() {
		instance = this;
		
		core = LoaderService.loader(this);
	}
	
	public static Workity instance() {
		if (instance == null) throw new IllegalStateException("The Workity instance is null.");
		
		return instance;
	}
	
	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();
		core.enable();
		
		LogUtils.info(
			"Created by InitSync. Enabled in " + (System.currentTimeMillis() - startTime) + "ms.",
			"You are using the version " + release + "."
		);
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

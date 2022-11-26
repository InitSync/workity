package me.bryang.workity.plugin.services;

import me.bryang.workity.Workity;
import me.bryang.workity.plugin.Loader;
import me.bryang.workity.plugin.loader.CommandLoader;
import me.bryang.workity.plugin.loader.DataLoader;
import me.bryang.workity.plugin.loader.EventLoader;

public interface LoaderService {
	static Loader loader(Workity plugin) {
		return new Loader(plugin);
	}
	
	static CommandLoader.Builder commandLoader(Workity plugin) {
		return new CommandLoader.Builder(plugin);
	}
	
	static EventLoader.Builder eventLoader(Workity plugin) {
		return new EventLoader.Builder(plugin);
	}
	
	static DataLoader dataLoader() {
		return new DataLoader();
	}
}

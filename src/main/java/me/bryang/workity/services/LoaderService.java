package me.bryang.workity.services;

import me.bryang.workity.Workity;
import me.bryang.workity.loader.CommandLoader;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.loader.EventLoader;

public interface LoaderService {
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

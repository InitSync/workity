package me.bryang.workity.utils;

import me.bryang.workity.Workity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {
	private static final Logger LOGGER = Workity.instance().getLogger();
	
	private LogUtils() {}
	
	public static void info(String... logs) {
		for (String log : logs) LOGGER.log(Level.INFO, log);
	}
	
	public static void warn(String... logs) {
		for (String log : logs) LOGGER.log(Level.WARNING, log);
	}
	
	public static void error(String... logs) {
		for (String log : logs) LOGGER.log(Level.SEVERE, log);
	}
}

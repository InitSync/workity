package me.bryang.workity.plugin.loader;

import me.bryang.workity.plugin.utils.LogUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CommandLoader {
	private CommandLoader() {}
	
	public static class Builder {
		private final JavaPlugin plugin;
		
		private String commandName;
		private CommandExecutor executor;
		private TabCompleter completer;
		
		public Builder(JavaPlugin plugin) {
			this.plugin = Objects.requireNonNull(plugin, "The JavaPlugin instance is null.");
		}
		
		public Builder command(String commandName) {
			this.commandName = Objects.requireNonNull(commandName, "The command name is empty.");
			Validate.notEmpty(commandName, "The command name is empty.");
			return this;
		}
		
		public Builder executor(CommandExecutor executor) {
			this.executor = Objects.requireNonNull(executor, "The command executor is null.");
			return this;
		}
		
		public Builder completer(TabCompleter completer) {
			this.completer = Objects.requireNonNull(completer, "The tab completer is null.");
			return this;
		}
		
		public void build() {
			if (commandName == null) {
				LogUtils.error("The command name is null to be used.");
				return;
			}
			
			if (executor == null) {
				LogUtils.error("The command executor is null to be used.");
				return;
			}
			
			PluginCommand command = plugin.getCommand(commandName);
			if (command == null) {
				LogUtils.error("The command '" + commandName + "' isn't registered at the plugin.yml file.");
				return;
			}
			
			command.setExecutor(executor);
			
			if (completer != null) {
				command.setTabCompleter(completer);
				completer = null;
			}
			
			commandName = null;
			executor = null;
			return;
		}
	}
}

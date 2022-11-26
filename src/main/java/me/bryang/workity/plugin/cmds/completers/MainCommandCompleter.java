package me.bryang.workity.plugin.cmds.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommandCompleter
implements TabCompleter {
	private final List<String> commandArgs;
	
	public MainCommandCompleter() {
		this.commandArgs = new ArrayList<>();
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return null;
		
		if (commandArgs.isEmpty()) {
			commandArgs.add("help");
			commandArgs.add("reload");
		}
		
		List<String> results = new ArrayList<>();
		
		if (args.length == 1) {
			for (String result : commandArgs) {
				if (result.toLowerCase().equals(args[0].toLowerCase())) results.add(result);
			}
			return results;
		}
		return null;
	}
}

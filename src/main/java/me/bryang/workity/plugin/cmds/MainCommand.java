package me.bryang.workity.plugin.cmds;

import me.bryang.workity.Workity;
import me.bryang.workity.plugin.enums.Permission;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.utils.TextUtils;
import net.xconfig.enums.Action;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MainCommand
implements CommandExecutor {
	private final BukkitConfigurationHandler configurationHandler;
	
	public MainCommand(BukkitConfigurationHandler configurationHandler) {
		this.configurationHandler = Objects.requireNonNull(configurationHandler, "The BukkitConfigurationHandler object is null.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player player)) return false;
		
		String prefix = configurationHandler.text("config.yml", "config.prefix");
		
		if (args.length == 0) {
			player.sendMessage(TextUtils.colorize(prefix + "&f Running with &eBukkit &7- &6" + Bukkit.getBukkitVersion()));
			player.sendMessage(TextUtils.colorize(prefix + "&f Developed by &aInitSync &8| &7v" + Workity.instance().release));
			return false;
		}
		
		switch (args[0]) {
			default -> {
				player.sendMessage(configurationHandler.text("messages.yml", "error.unknown-argument")
					.replace("<prefix>", prefix));
			}
			case "help" -> {
				if (!player.hasPermission(Permission.HELP_CMD.getPermission())) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.no-permission")
						.replace("<prefix>", prefix));
				}
				
				configurationHandler.textList("messages.yml", "jobs.help.admin").forEach(player::sendMessage);
			}
			case "reload" -> {
				if (!player.hasPermission(Permission.RELOAD_CMD.getPermission())) {
					player.sendMessage(configurationHandler.text("messages.yml", "error.no-permission")
						.replace("<prefix>", prefix));
					break;
				}
				
				configurationHandler.doSomething("config.yml", Action.RELOAD, null, null);
				configurationHandler.doSomething("messages.yml", Action.RELOAD, null, null);
			}
		}
		return false;
	}
}

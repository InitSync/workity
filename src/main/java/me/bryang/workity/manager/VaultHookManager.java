package me.bryang.workity.manager;

import me.bryang.workity.Workity;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

import java.util.Objects;
import java.util.logging.Logger;

public class VaultHookManager {
	private final Workity workity;
	private final Logger logger;
	
	private Economy economy;
	private Permission permission;
	
	public VaultHookManager(Workity workity, Logger logger) {
		this.workity = Objects.requireNonNull(workity, "The Workity instance is null.");
		this.logger = Objects.requireNonNull(logger, "The Logger object is null.");
	}
	
	public void load() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		if (!pluginManager.isPluginEnabled("Vault")) {
			
			logger.info(" Error: You need Vault to load the plugin");
			pluginManager.disablePlugin(workity);
			return;
		}
		
		ServicesManager servicesManager = Bukkit.getServicesManager();
		
		RegisteredServiceProvider<Economy> economyProvider = servicesManager.getRegistration(Economy.class);
		RegisteredServiceProvider<Permission> permissionProvider = servicesManager.getRegistration(Permission.class);
		if (economyProvider == null) {
			logger.info(" Error: The economy doesn't loaded correctly.");
			return;
		}
		
		if (permissionProvider == null) {
			logger.info(" Error: The permission doesn't loaded correctly.");
			return;
		}
		
		economy = economyProvider.getProvider();
		permission = permissionProvider.getProvider();
	}
	
	public Economy getEconomy() {
		return economy;
	}
	
	public Permission getPermission() {
		return permission;
	}
}

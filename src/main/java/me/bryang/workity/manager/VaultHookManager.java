package me.bryang.workity.manager;

import me.bryang.workity.Workity;
import me.bryang.workity.utils.LogUtils;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

import java.util.Objects;

public class VaultHookManager {
	private final Workity workity;
	
	private Economy economy;
	private Permission permission;
	
	public VaultHookManager(Workity workity) {
		this.workity = Objects.requireNonNull(workity, "The Workity instance is null.");
	}
	
	public void load() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		if (!pluginManager.isPluginEnabled("Vault")) {
			LogUtils.error("You need Vault to load the plugin");
			pluginManager.disablePlugin(workity);
			return;
		}
		
		ServicesManager servicesManager = Bukkit.getServicesManager();
		
		RegisteredServiceProvider<Economy> economyProvider = servicesManager.getRegistration(Economy.class);
		RegisteredServiceProvider<Permission> permissionProvider = servicesManager.getRegistration(Permission.class);
		if (economyProvider == null) {
			LogUtils.info("The economy doesn't loaded correctly.");
			return;
		}
		
		if (permissionProvider == null) {
			LogUtils.info("The permission doesn't loaded correctly.");
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

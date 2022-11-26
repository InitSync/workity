package me.bryang.workity.plugin.manager;

import me.bryang.workity.plugin.utils.LogUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHookManager {
	private Economy economy;
	
	public VaultHookManager() {}
	
	public void load() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		if (pluginManager.getPlugin("Vault") != null || !pluginManager.isPluginEnabled("Vault")) {
			LogUtils.error("Vault not found! Avoiding economy provider.");
			return;
		}
		
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
		if (economyProvider == null) {
			LogUtils.info("The economy doesn't loaded correctly.");
			return;
		}
		
		economy = economyProvider.getProvider();
	}
	
	public boolean isRegistered() {
		return economy != null;
	}
	
	public Economy getEconomy() {
		return economy;
	}
}

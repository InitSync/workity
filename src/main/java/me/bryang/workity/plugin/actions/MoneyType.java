package me.bryang.workity.plugin.actions;

import me.bryang.workity.api.enums.Action;
import me.bryang.workity.api.models.ExecutableModel;
import me.bryang.workity.plugin.manager.VaultHookManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MoneyType
implements ExecutableModel {
	private final VaultHookManager vaultHookManager;
	
	public MoneyType(VaultHookManager vaultHookManager) {
		this.vaultHookManager = Objects.requireNonNull(vaultHookManager, "The VaultHookManager object is null.");
	}
	
	@Override
	public Action type() {
		return Action.MONEY;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		if (!vaultHookManager.isRegistered()) return;
		
		vaultHookManager.getEconomy().depositPlayer(player, Double.parseDouble(container));
	}
}

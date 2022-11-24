package me.bryang.workity.actions.types;

import me.bryang.workity.actions.ExecutableModel;
import me.bryang.workity.manager.VaultHookManager;
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
	public Type actionType() {
		return Type.MONEY;
	}
	
	@Override
	public void execute(JavaPlugin plugin, Player player, String container) {
		if (!vaultHookManager.isRegistered()) return;
		
		vaultHookManager.getEconomy().depositPlayer(player, Double.parseDouble(container));
	}
}

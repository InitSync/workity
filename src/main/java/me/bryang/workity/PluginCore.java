package me.bryang.workity;

import me.bryang.workity.activites.JobsLoadingActivity;
import me.bryang.workity.activites.MetricsLoadingActivities;
import me.bryang.workity.activites.PluginLoadingActivities;
import me.bryang.workity.database.Database;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.manager.ActivitiesManager;
import me.bryang.workity.manager.FileConverter;
import me.bryang.workity.manager.VaultHookManager;
import me.bryang.workity.manager.WorkActionManager;
import me.bryang.workity.manager.jobs.*;
import me.bryang.workity.services.DatabaseService;
import me.bryang.workity.services.LoaderService;
import me.bryang.workity.services.ManagerService;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.services.ConfigurationService;
import org.bukkit.Bukkit;

public class PluginCore
implements Core {
	private BukkitConfigurationModel configurationManager;
	private BukkitConfigurationHandler configurationHandler;
	private DataLoader dataLoader;
	private Database database;
	private ActivitiesManager activitiesManager;
	private FileConverter fileConverter;
	private VaultHookManager vaultHookManager;
	private WorkActionManager workActionManager;
	
	public PluginCore(Workity plugin) {
		this.configurationManager = ConfigurationService.bukkitManager(plugin);
		this.configurationHandler = ConfigurationService.bukkitHandler(configurationManager);
		this.dataLoader = LoaderService.dataLoader();
		this.database = DatabaseService.playerFilesDatabase(configurationHandler);
		this.activitiesManager = ManagerService.activitiesManager();
		this.fileConverter = ManagerService.fileConverter(configurationManager, configurationHandler);
		this.vaultHookManager = ManagerService.vaultHookManager(plugin);
		this.workActionManager = ManagerService.workActionManager();
	}
	
	@Override
	public void enable() {
		configurationManager.create("",
			"config.yml",
			"messages.yml",
			"players.yml");
		configurationManager.create("jobs",
			"builder.yml",
			"hunter.yml",
			"miner.yml",
			"woodcutter.yml");
		configurationManager.load("config.yml",
			"messages.yml",
			"players.yml",
			"builder.yml",
			"hunter.yml",
			"miner.yml",
			"woodcutter.yml");
		
		vaultHookManager.load();
		workActionManager.addActions(new JobActionRewardsManager(configurationHandler, vaultHookManager),
			new JobAddStatsPointsManager(database, dataLoader),
			new JobGainRewardManager(dataLoader,
				configurationHandler,
				database,
				vaultHookManager),
			new JobIncreaseLevelManager(configurationHandler, database),
			new JobSetMultiplierManager(configurationHandler, vaultHookManager));
		
		activitiesManager.loadActivities(new JobsLoadingActivity(dataLoader, fileConverter),
			new MetricsLoadingActivities(configurationHandler),
			new PluginLoadingActivities(configurationHandler, database, dataLoader));
	}
	
	@Override
	public void disable() {
		if (configurationManager != null) configurationManager = null;
		if (configurationHandler != null) configurationHandler = null;
		
		if (database != null) database = null;
		if (dataLoader != null) dataLoader = null;
		
		if (activitiesManager != null) activitiesManager = null;
		if (fileConverter != null) fileConverter = null;
		if (vaultHookManager != null) vaultHookManager = null;
		if (workActionManager != null) workActionManager = null;
	}
}

package me.bryang.workity.plugin;

import me.bryang.workity.Workity;
import me.bryang.workity.api.LoaderModel;
import me.bryang.workity.api.database.Database;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.api.managers.ActionModelManager;
import me.bryang.workity.api.managers.ActivityModelManager;
import me.bryang.workity.api.managers.FileModelConverter;
import me.bryang.workity.api.managers.WorkModelManager;
import me.bryang.workity.plugin.actions.*;
import me.bryang.workity.plugin.activities.JobsLoadingActivity;
import me.bryang.workity.plugin.activities.PluginLoadingActivity;
import me.bryang.workity.plugin.cmds.MainCommand;
import me.bryang.workity.plugin.cmds.completers.MainCommandCompleter;
import me.bryang.workity.plugin.listeners.*;
import me.bryang.workity.plugin.manager.VaultHookManager;
import me.bryang.workity.plugin.manager.jobs.*;
import me.bryang.workity.plugin.services.DatabaseService;
import me.bryang.workity.plugin.services.LoaderService;
import me.bryang.workity.plugin.services.ManagerService;
import net.xconfig.XConfig;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;

import java.util.Objects;

public class Loader
implements LoaderModel {
	private final Workity plugin;
	
	private BukkitConfigurationModel configurationManager;
	private BukkitConfigurationHandler configurationHandler;
	private DataModelLoader dataLoader;
	private Database database;
	private ActivityModelManager activitiesManager;
	private FileModelConverter fileConverter;
	private VaultHookManager vaultHookManager;
	private WorkModelManager workActionManager;
	private ActionModelManager actionManager;
	
	public Loader(Workity plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Workity instance is null.");
		this.configurationManager = XConfig.bukkitManager(plugin);
		this.configurationHandler = XConfig.bukkitHandler(configurationManager);
		this.dataLoader = LoaderService.dataLoader();
		this.database = DatabaseService.playerFilesDatabase(configurationHandler);
		this.activitiesManager = ManagerService.activitiesManager();
		this.fileConverter = ManagerService.fileConverter(configurationManager, configurationHandler);
		this.vaultHookManager = ManagerService.vaultHookManager();
		this.workActionManager = ManagerService.workActionManager();
		this.actionManager = ManagerService.actionManager(plugin);
	}
	
	@Override
	public void enable() {
		configurationManager.create("", "config.yml", "messages.yml", "players.yml");
		configurationManager.create(
			"jobs",
			"builder.yml",
			"hunter.yml",
			"miner.yml",
			"woodcutter.yml"
		);
		configurationManager.load(
			"config.yml",
			"messages.yml",
			"players.yml",
			"builder.yml",
			"hunter.yml",
			"miner.yml",
			"woodcutter.yml"
		);
		
		vaultHookManager.load();
		workActionManager.registerActions(
			new JobActionRewardsManager(configurationHandler, actionManager),
			new JobAddStatsPointsManager(database, dataLoader),
			new JobGainRewardManager(dataLoader, configurationHandler, database, vaultHookManager),
			new JobIncreaseLevelManager(configurationHandler, database),
			new JobSetMultiplierManager(configurationHandler, vaultHookManager)
		);
		
		if (configurationHandler.condition("config.yml", "enabled-metrics")) {
			new Metrics(plugin, 16726);
		}
		
		activitiesManager.loadActivities(
			new JobsLoadingActivity(plugin, dataLoader, fileConverter),
			new PluginLoadingActivity(configurationHandler, database, dataLoader)
		);
		
		actionManager.registerActions(
			new SoundType(),
			new TitleType(),
			new ActionBarType(),
			new CommandType(),
			new BroadcastType(),
			new MoneyType(vaultHookManager),
			new ConnectType(),
			new MessageType()
		);
		
		LoaderService.eventLoader(plugin)
			 .providers(
					new PlayerRegistryListener(dataLoader),
				  new WorkBreakListener(configurationHandler),
				  new WorkDeathListener(configurationHandler),
				  new WorkExecutorListener(dataLoader, workActionManager, dataLoader.jobData()),
				  new WorkFurnaceListener(configurationHandler, database, dataLoader),
				  new WorkPlaceListener(configurationHandler)
			 )
			 .build();
		
		LoaderService.commandLoader(plugin)
			 .command("workity")
			 .executor(new MainCommand(configurationHandler))
			 .completer(new MainCommandCompleter())
			 .build();
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
		if (workActionManager != null) {
			workActionManager.unregisterAll();
			workActionManager = null;
		}
		if (actionManager != null) {
			actionManager.unregister();
			actionManager = null;
		}
	}
}

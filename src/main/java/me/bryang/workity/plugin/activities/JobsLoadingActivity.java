package me.bryang.workity.plugin.activities;

import me.bryang.workity.Workity;
import me.bryang.workity.api.loaders.DataModelLoader;
import me.bryang.workity.api.managers.FileModelConverter;
import me.bryang.workity.api.models.ActivityModel;
import me.bryang.workity.plugin.data.jobs.JobData;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class JobsLoadingActivity
implements ActivityModel {
	private final Workity plugin;
	private final DataModelLoader dataLoader;
	private final FileModelConverter fileConverter;
	
	public JobsLoadingActivity(
		 Workity plugin,
		 DataModelLoader dataLoader,
		 FileModelConverter fileConverter) {
		this.plugin = Objects.requireNonNull(plugin, "The Workity instance is null.");
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
		this.fileConverter = Objects.requireNonNull(fileConverter, "The FileConverter instance is null.");
	}
	
	@Override
	public void load() {
		Map<String, JobData> jobData = dataLoader.jobData();
		String path = plugin.getDataFolder().getPath() + "/jobs";
		
		for (String fileName : new File(path).list()) {
			JobData data = fileConverter.convert(fileName);
			String jobName = data.getJobName();
			jobData.put(data.getJobName(), data);
		}
	}
}

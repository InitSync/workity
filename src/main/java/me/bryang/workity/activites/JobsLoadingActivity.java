package me.bryang.workity.activites;

import me.bryang.workity.Workity;
import me.bryang.workity.data.jobs.JobData;
import me.bryang.workity.loader.DataLoader;
import me.bryang.workity.manager.FileConverter;

import java.io.File;
import java.util.Objects;

public class JobsLoadingActivity implements Activities {
	private final DataLoader dataLoader;
	private final FileConverter fileConverter;
	
	public JobsLoadingActivity(DataLoader dataLoader, FileConverter fileConverter) {
		this.dataLoader = Objects.requireNonNull(dataLoader, "The DataLoader object is null.");
		this.fileConverter = Objects.requireNonNull(fileConverter, "The FileConverter instance is null.");
	}
	
	public void loadTask() {
		for (String fileName : new File(Workity.instance()
			.getDataFolder()
			.getPath() + "/jobs")
			.list()
		) {
			JobData jobData = fileConverter.convert(fileName);
			dataLoader.jobData().put(jobData.getJobName(), jobData);
		}
	}
}

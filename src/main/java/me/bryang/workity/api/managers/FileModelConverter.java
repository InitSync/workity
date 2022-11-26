package me.bryang.workity.api.managers;

import me.bryang.workity.plugin.data.jobs.JobData;

public interface FileModelConverter {
	JobData convert(String fileName);
}

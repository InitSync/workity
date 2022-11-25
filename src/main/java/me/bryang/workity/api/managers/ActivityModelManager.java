package me.bryang.workity.api.managers;

import me.bryang.workity.api.models.ActivityModel;

public interface ActivityModelManager {
	default void loadActivity(ActivityModel activityModel) {
		activityModel.load();
	}
	
	void loadActivities(ActivityModel... activityModels);
}

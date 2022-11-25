package me.bryang.workity.plugin.manager;

import me.bryang.workity.api.managers.ActivityModelManager;
import me.bryang.workity.api.models.ActivityModel;

public class ActivityManager
implements ActivityModelManager {
	public ActivityManager() {}
	
	@Override
	public void loadActivities(ActivityModel... activityModels) {
		for (ActivityModel activityModel : activityModels) {
			activityModel.load();
		}
	}
}

package me.bryang.workity.manager;

import me.bryang.workity.activites.Activity;

public class ActivitiesManager {
	public ActivitiesManager() {}
	
	public void loadActivities(Activity... activities) {
		for (Activity activity : activities) {
			activity.loadTask();
		}
	}
}

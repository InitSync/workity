package me.bryang.workity.manager;

import me.bryang.workity.activites.Activities;

public class ActivitiesManager {
	public ActivitiesManager() {}
	
	public void loadActivities(Activities... activities) {
		for (Activities activity : activities) {
			activity.loadTask();
		}
	}
}

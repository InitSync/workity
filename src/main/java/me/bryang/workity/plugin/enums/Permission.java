package me.bryang.workity.plugin.enums;

public enum Permission {
	HELP_CMD ("workity.help"),
	RELOAD_CMD ("workity.reload"),
	JOBS_CMD ("workity.jobs");
	
	private final String permission;
	
	Permission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}

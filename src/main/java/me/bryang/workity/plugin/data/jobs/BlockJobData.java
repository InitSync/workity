package me.bryang.workity.plugin.data.jobs;

public class BlockJobData {
	private final int gainXP;
	private final int gainMoney;
	private final boolean enableStatus;
	
	public BlockJobData(int gainMoney, int gainXP, boolean enableStatus) {
		this.gainMoney = gainMoney;
		this.gainXP = gainXP;
		this.enableStatus = enableStatus;
	}
	
	public int getGainXP() {
		return gainXP;
	}
	
	public int getGainMoney() {
		return gainMoney;
	}
	
	public boolean isDisableStatus() {
		return !enableStatus;
	}
}

package me.bryang.workity.utils;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MathLevelsUtils {
	private MathLevelsUtils() {}
	
	public static double calculateDoubleNumber(String format, int level) {
		return new ExpressionBuilder(format.replace("%level%", Integer.toString(level)))
			.build()
			.evaluate();
	}
	
	public static int calculateNumber(String format, int level) {
		return (int) new ExpressionBuilder(format.replace("%level%", Integer.toString(level)))
			.build()
			.evaluate();
	}
}

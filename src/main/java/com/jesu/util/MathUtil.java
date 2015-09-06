package com.jesu.util;

public class MathUtil {

	public static double getCosX(double centerX, double distance, double direction) {
		return centerX + Math.cos(direction) * distance;
	}

	public static double getCosX(double distance, double direction) {
		return Math.cos(direction) * distance;
	}

	public static double getSinY(double centerY, double distance, double direction) {
		return centerY + Math.sin(direction) * distance;
	}

	public static double getSinY(double distance, double direction) {
		return Math.sin(direction) * distance;
	}

	public static int getCenterX(double topX, double width) {
		return (int) (topX + width / 2);
	}

	public static int getCenterY(double topY, double height) {
		return (int) (topY + height / 2);
	}

	public static int getTopX(int centerX, int width) {
		return centerX - width / 2;
	}

	public static int getTopY(int centerY, int height) {
		return centerY - height / 2;
	}

}

package com.jesu.util;

import java.awt.Point;
import java.awt.Rectangle;

public class RectangleUtil {

	public static Point getCenter(Rectangle rectangle) {
		if (rectangle == null) {
			return null;
		}
		return new Point((int) rectangle.getCenterX(), (int) rectangle.getCenterY());
	}

}

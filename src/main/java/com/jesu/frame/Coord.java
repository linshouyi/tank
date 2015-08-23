package com.jesu.frame;

import java.awt.Graphics;
import java.awt.Point;

/**
 * 坐标显示
 * 
 * @author linshouyi
 *
 */
public class Coord {

	private Point point;

	public void paint(Graphics g) {
		if (point == null) {
			return;
		}
		g.drawString("[" + point.x + " " + point.y + "]", 10, 35);
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}

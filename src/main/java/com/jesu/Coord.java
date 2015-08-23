package com.jesu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 坐标显示
 * 
 * @author linshouyi
 *
 */
public class Coord implements Painter {

	private Point point;

	@Override
	public void paint(Graphics g) {
		if (point == null) {
			return;
		}
		g.drawString("[" + point.x + " " + point.y + "]", 10, 35);
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public Rectangle getRectangle() {
		return null;
	}

}

package com.jesu.painter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.jesu.frame.BasicPainter;

/**
 * 坐标显示
 * 
 * @author linshouyi
 *
 */
public class Coord extends BasicPainter {

	private Point point;

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public void paint(Graphics2D g) {
		if (point == null) {
			return;
		}
		g.setColor(Color.RED);
		g.drawString("[" + point.x + " " + point.y + "]", 10, 35);
	}

	@Override
	public void action() {
	}

}

package com.jesu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 选择框
 * 
 * @author linshouyi
 *
 */
public class Selector implements Life, Painter {

	private Point startPoint;
	private Point endPoint;
	private boolean live;

	public Selector(Point startPoint, Point endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.live = true;
	}

	public Selector(Point startPoint) {
		this(startPoint, startPoint);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 3 }, 0));
		g2d.draw(getRectangle());
	}

	@Override
	public void action() {
		int startX = (endPoint.x - startPoint.x) / 4;
		int startY = (endPoint.y - startPoint.y) / 4;
		int endX = (startPoint.x - endPoint.x) / 4;
		int endY = (startPoint.y - endPoint.y) / 4;
		startPoint.x += startX;
		startPoint.y += startY;
		endPoint.x += endX;
		endPoint.y += endY;
		// System.out.println(startPoint.x + " " + startPoint.y + " " + endPoint.x + " " + endPoint.y);
		if (Math.abs(startPoint.x - endPoint.x) < 20 && Math.abs(startPoint.y - endPoint.y) < 20) {
			this.live = false;
		}
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public boolean isLive() {
		return this.live;
	}

	@Override
	public Rectangle getRectangle() {
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int width = Math.abs(endPoint.x - startPoint.x);
		int height = Math.abs(endPoint.y - startPoint.y);
		return new Rectangle(x, y, width, height);
	}

}

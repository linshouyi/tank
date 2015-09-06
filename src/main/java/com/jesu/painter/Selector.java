package com.jesu.painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.jesu.frame.BasicRec;
import com.jesu.frame.GameResource;

/**
 * 选择器
 * 
 * @author linshouyi
 *
 */
public class Selector extends BasicRec {

	private Point point;// 起始点
	private boolean finish;

	public Selector(int x, int y) {
		this(x, y, 0, 0);
	}

	public Selector(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.point = new Point(x, y);
		this.finish = false;
		GameResource.add(this);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 3 }, 0));
		g.draw(this.rectangle);
	}

	@Override
	public void action() {
		if (finish) {
			int x = this.rectangle.x + this.rectangle.width / 4;
			int y = this.rectangle.y + this.rectangle.height / 4;
			int width = this.rectangle.height / 2;
			int height = this.rectangle.height / 2;
			this.rectangle.setBounds(x, y, width, height);
			if (width <= 20 && height <= 20) {
				this.die();
			}
		}
	}

	public void setEndPoint(int x, int y) {
		this.rectangle.setBounds(Math.min(point.x, x), Math.min(point.y, y), Math.abs(point.x - x), Math.abs(point.y - y));
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public void finish() {
		this.finish = true;
	}

	public boolean isFinish() {
		return finish;
	}
}

package com.jesu.life.block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.jesu.Entity;

/**
 * 障碍物
 * 
 * @author linshouyi
 *
 */
public class Block implements Entity {

	private Rectangle rectangle;
	private Color color;
	private boolean live;

	public Block(int x, int y, int width, int height) {
		this.rectangle = new Rectangle(x, y, width, height);
		this.color = Color.green;
		this.live = true;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fill(rectangle);
		g.setColor(Color.BLACK);
		g.drawString("树林", rectangle.x, (int) rectangle.getCenterY());
		// g.setColor(Color.RED);
		// g.draw(rectangle);
	}

	@Override
	public Rectangle getRectangle() {
		return this.rectangle;
	}

	@Override
	public boolean isLive() {
		return this.live;
	}

}

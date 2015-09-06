package com.jesu.painter.block;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jesu.frame.BasicEntity;
import com.jesu.frame.GameResource;

/**
 * 障碍物
 * 
 * @author linshouyi
 *
 */
public class Block extends BasicEntity {

	public Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		GameResource.add(this);
	}

	@Override
	public void action() {
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fill(rectangle);
	}
}

package com.jesu.painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.jesu.frame.BasicPainter;
import com.jesu.frame.Controller;
import com.jesu.frame.GameResource;

/**
 * 已选标志
 * 
 * @author linshouyi
 *
 */
public class Selected extends BasicPainter {

	private Controller controller;

	public Selected(Controller controller) {
		super();
		this.controller = controller;
		GameResource.add(this);
	}

	@Override
	public boolean isAlive() {
		return this.controller.isAlive() && this.controller.isControlled();
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 3 }, 0));
		g.draw(this.controller.getRectangle());
	}

	@Override
	public void action() {

	}

}

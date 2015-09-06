package com.jesu.painter.weapon;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import com.jesu.frame.BasicPainter;
import com.jesu.frame.GameResource;
import com.jesu.painter.cannonball.Cannonball;
import com.jesu.painter.tank.BasicTank;
import com.jesu.util.MathUtil;

/**
 * 炮筒
 * 
 * @author linshouyi
 *
 */
public class Cannon extends BasicPainter {

	private BasicTank tank;
	private int length;
	private int width;

	public Cannon(BasicTank tank, int length, int width) {
		this.tank = tank;
		this.length = length;
		this.width = width;
		GameResource.add(this);
	}

	@Override
	public void action() {
	}

	@Override
	public boolean isAlive() {
		return this.tank.isAlive() && super.isAlive();
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(GameResource.getTeamColor(tank.getTeam()));
		int x = (int) this.tank.getRectangle().getCenterX();
		int y = (int) this.tank.getRectangle().getCenterY();
		double x1 = MathUtil.getCosX(x, length, tank.getDirection());
		double y1 = MathUtil.getSinY(y, length, this.tank.getDirection());
		g.setStroke(new BasicStroke(width));
		g.drawLine(x, y, (int) x1, (int) y1);
	}

	public void fire() {
		int x = (int) this.tank.getRectangle().getCenterX();
		int y = (int) this.tank.getRectangle().getCenterY();
		int width = Math.max(2, this.width);
		new Cannonball(this.tank, x - width / 2, y - width / 2, width, width, tank.getDirection());
	}

}

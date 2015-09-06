package com.jesu.painter;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jesu.frame.BasicPainter;
import com.jesu.frame.GameResource;
import com.jesu.painter.tank.BasicTank;

/**
 * 视野范围
 * 
 * @author linshouyi
 *
 */
public class ViewRange extends BasicPainter {

	private BasicTank tank;

	public ViewRange(BasicTank tank) {
		this.tank = tank;
		GameResource.add(this);
	}

	@Override
	public void paint(Graphics2D g) {
		if (GameResource.isViewRange()) {
			int width = Math.max(10, tank.getRectangle().width);
			int height = Math.max(2, width / 10);
			// int x = tank.getRectangle().x;
			int x = tank.getRectangle().x + (tank.getRectangle().width - width) / 2;
			int y = tank.getRectangle().y - height * 2;
			double rate = 0;
			if (tank.getHp() != 0 && tank.getFullHp() != 0) {
				rate = (double) tank.getHp() / tank.getFullHp();
			}
			g.setColor(Color.RED);
			g.fillRect(x, y, (int) (width * rate), height);
			g.setColor(GameResource.getTeamColor(tank.getTeam()));
			g.drawRect(x, y, width, height);
		}
	}

	@Override
	public void action() {
	}

	@Override
	public boolean isAlive() {
		return super.isAlive() && this.tank.isAlive();
	}

}

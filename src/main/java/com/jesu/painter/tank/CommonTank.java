package com.jesu.painter.tank;

import com.jesu.frame.GameResource;
import com.jesu.painter.weapon.Cannon;

/**
 * 普通坦克
 * 
 * @author linshouyi
 *
 */
public class CommonTank extends BasicTank {

	public CommonTank(int team, int x, int y, int width, int height, double direction) {
		super(team, x, y, width, height, 10, 3, direction);
		this.cannon = new Cannon(this, (int) (width * 0.6), (int) (width * 0.2));
		this.attack = 3;
		this.defense = 1;
		this.attackDis = 50;
		this.attackFre = 1;
		this.viewDis = 100;
		GameResource.add(this);
	}

}

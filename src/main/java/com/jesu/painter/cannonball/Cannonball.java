package com.jesu.painter.cannonball;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import com.jesu.frame.BasicRec;
import com.jesu.frame.Entity;
import com.jesu.frame.GameResource;
import com.jesu.painter.tank.BasicTank;
import com.jesu.util.MathUtil;

/**
 * 炮弹
 * 
 * @author linshouyi
 *
 */
public class Cannonball extends BasicRec {

	private BasicTank tank;// 所属坦克
	private Point point;// 发射点
	private double direction;// 方向
	private int speed;// 速度
	private int distance;// 行驶距离

	public Cannonball(BasicTank tank, int x, int y, int width, int height, double direction) {
		super(x, y, width, height);
		this.tank = tank;
		this.point = new Point(x, y);
		this.direction = direction;
		this.speed = 5;
		GameResource.add(this);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(GameResource.getTeamColor(tank.getTeam()));
		g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	@Override
	public void action() {
		if (distance > tank.getAttackDis()) {
			this.die();
			return;
		}
		List<Entity> entities = GameResource.intersectAll(tank, rectangle.x, rectangle.y, rectangle.width, rectangle.height);// 击中的实体
		boolean hit = false;
		for (Entity entity : entities) {
			if (entity instanceof BasicTank) {
				BasicTank target = (BasicTank) entity;
				if (target.getTeam() != tank.getTeam()) {
					target.hurt(tank);
//					System.out.println(tank + "击中了" + target);
					hit = true;
				}
			}
		}
		if (hit) {
			this.die();
		} else {
			distance += speed;
			int x = (int) MathUtil.getCosX(this.point.x, distance, direction);
			int y = (int) MathUtil.getSinY(this.point.y, distance, direction);
			this.rectangle.setLocation(x, y);
		}
	}

	@Override
	public void die() {
		super.die();
		this.tank.addAttackFre();
	}
}

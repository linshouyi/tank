package com.jesu.life.tank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.jesu.Controller;
import com.jesu.adapter.AkTankAdapter;
import com.jesu.frame.Resource;

/**
 * Ak坦克
 * 
 * @author linshouyi
 *
 */
public class AkTank implements Controller {

	private boolean live;
	private Rectangle rectangle;
	private int speed;
	private Color color;
	private Point target;

	private AkTankAdapter adapter;

	public AkTank(int x, int y) {
		this(x, y, 20, 20);
	}

	public AkTank(int x, int y, int width, int height) {
		super();
		this.live = true;
		this.rectangle = new Rectangle(x, y, width, height);
		this.speed = 3;
		this.color = Color.black;
		adapter = new AkTankAdapter(this);
	}

	public Point plan() {
		Point target = this.target;
		if (target == null) {
			return null;
		}
		Point location = this.rectangle.getLocation();
		double distance = target.distance(location);
		if (distance == 0) {
			return null;
		}
		if (distance > speed) {
			int x = location.x;
			int y = location.y;
			x += (int) (speed * (target.x - x) / distance);
			y += (int) (speed * (target.y - y) / distance);
			return new Point(x, y);
		} else {
			return target;
		}
	}

	public void move(Point point) {
		this.rectangle.setLocation(point);
	}

	@Override
	public void paint(Graphics2D g) {
		this.action();
		g.setColor(color);
		g.fill(rectangle);
	}

	public void setTarget(Point target) {
		this.target = target;
	}

	public void action() {
		Point nextLocation = plan();
		boolean allow = false;
		if (nextLocation != null) {
			allow = !Resource.getResource().intersects(this, nextLocation, this.rectangle.getSize());
		}
		if (allow) {
			move(nextLocation);
		}
	}

	@Override
	public boolean isLive() {
		return live;
	}

	@Override
	public Rectangle getRectangle() {
		return this.rectangle;

	}

	public AkTankAdapter getAdapter() {
		return adapter;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.remove(4);
	}
}

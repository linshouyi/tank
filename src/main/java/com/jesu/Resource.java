package com.jesu;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.jesu.tank.AkTank;

/**
 * 游戏资源
 * 
 * @author linshouyi
 *
 */
public class Resource {

	private static Resource resource;
	private List<Painter> painters = new ArrayList<Painter>();
	private List<AkTank> tanks = new ArrayList<AkTank>();

	private Resource() {
	}

	public static Resource getResource() {
		if (resource == null) {
			synchronized (Resource.class) {
				if (resource == null) {
					resource = new Resource();
				}
			}
		}
		return resource;
	}

	public List<Painter> getPainters() {
		return painters;
	}

	public List<AkTank> getTanks() {
		return tanks;
	}

	public boolean intersects(AkTank current, Point location, Dimension size) {
		for (AkTank tank : tanks) {
			if (current == tank) {
				continue;
			}
			if (tank.getRectangle().intersects(location.x, location.y, size.width, size.height)) {
				System.out.println(current + "要和" + tank + "相撞了！");
				return true;
			}
		}
		return false;
	}

	public boolean addPainter(Painter painter) {
		return this.painters.add(painter);
	}

	public boolean addTank(AkTank tank) {
		this.tanks.add(tank);
		return this.painters.add(tank);
	}

}

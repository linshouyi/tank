package com.jesu.frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.jesu.Controller;
import com.jesu.Entity;
import com.jesu.Painter;
import com.jesu.life.block.Block;
import com.jesu.life.tank.AkTank;

/**
 * 游戏资源
 * 
 * @author linshouyi
 *
 */
public class Resource {

	private static Resource resource;
	private List<Painter> painters = new ArrayList<Painter>();
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Controller> controllers = new ArrayList<Controller>();

	private List<AkTank> tanks = new ArrayList<AkTank>();
	private List<Block> blocks = new ArrayList<Block>();

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

	/**
	 * 碰撞检测实体
	 * 
	 * @param current
	 * @param location
	 * @param size
	 * @return
	 */
	public boolean intersects(Entity current, Point location, Dimension size) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isLive()) {
				entities.remove(i);
				i--;
			}
			if (current == entity) {
				continue;
			}
			if (entity.getRectangle().intersects(location.x, location.y, size.width, size.height)) {
				System.out.println(current + "要和" + entity + "相撞了！");
				return true;
			}
		}
		return false;
	}

	/**
	 * 绘制可绘制的
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		for (int i = 0; i < painters.size(); i++) {
			Painter painter = painters.get(i);
			if (!painter.isLive()) {
				painters.remove(i);
				i--;
			}
			painter.paint((Graphics2D) g.create());
		}
	}

	/**
	 * 控制可控制的
	 * 
	 * @param rectangle
	 * @return
	 */
	public List<Controller> control(Rectangle rectangle) {
		List<Controller> result = new ArrayList<Controller>();
		for (int i = 0; i < controllers.size(); i++) {
			Controller controller = controllers.get(i);
			if (!controller.isLive()) {
				controllers.remove(i);
				i--;
			}
			if (controller.getRectangle().intersects(rectangle) || controller.getRectangle().contains(rectangle.getLocation())) {
				result.add(controller);
				System.out.println("控制了：" + controller);
			}
		}
		return result;
	}

	public void addTank(AkTank tank) {
		this.painters.add(tank);
		this.entities.add(tank);
		this.controllers.add(tank);
		this.tanks.add(tank);
	}

	public void addBlock(Block block) {
		this.painters.add(block);
		this.entities.add(block);
		this.blocks.add(block);
	}
}

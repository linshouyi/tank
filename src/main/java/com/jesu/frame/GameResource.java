package com.jesu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jesu.painter.Selector;
import com.jesu.painter.tank.BasicTank;
import com.jesu.painter.tank.CommonTank;
import com.jesu.util.RectangleUtil;

/**
 * 游戏资源
 * 
 * @author linshouyi
 *
 */
public class GameResource {

	public static final int PLAY_TEAM = 1;// 玩家阵营
	public static final int COMP_TERM = 2;// 电脑阵营
	public static final int NEUT_TEAM = 3;// 中立阵营

	public static final Color PLAY_COLOR = Color.RED;
	public static final Color COMP_COLOR = Color.BLACK;
	public static final Color NEUT_COLOR = Color.GREEN;
	public static final Color OTHER_COLOR = Color.GRAY;

	private static List<Painter> painters = new ArrayList<Painter>();
	private static List<Entity> entities = new ArrayList<Entity>();
	private static List<Controller> controllers = new ArrayList<Controller>();
	// private List<AkTank> tanks = new ArrayList<AkTank>();
	// private List<Block> blocks = new ArrayList<Block>();

	private static Selector selector;// 选择器
	private static boolean hpBar;// 是否显示血量
	private static boolean viewRange = true;// 是否显示视野范围

	private GameResource() {
	}

	public static void createPlayTanks() {
		for (int i = 1; i <= 10; i++) {
			int width = 10;
			CommonTank tank = new CommonTank(GameResource.PLAY_TEAM, 0, i * (width + 5) + 30, width, width, 0);
			int x = (int) (tank.getRectangle().getCenterX() + 50);
			int y = (int) (tank.getRectangle().getCenterY());
			tank.setCommand(Command.getMoveCommand(new Point(x, y)));
		}
	}

	public static void createCompTanks() {
		for (int i = 1; i <= 20; i++) {
			int width = 10;
			CommonTank tank = new CommonTank(GameResource.COMP_TERM, 500, i * (width + 5) + 30, width, width, Math.PI);
			// tank.setViewDis(1000);
			int x = (int) (tank.getRectangle().getCenterX() - 50);
			int y = (int) (tank.getRectangle().getCenterY());
			tank.setCommand(Command.getMoveCommand(new Point(x, y)));
			tank = new CommonTank(GameResource.COMP_TERM, 550, i * (width + 5) + 30, width, width, Math.PI);
			x = (int) (tank.getRectangle().getCenterX() - 50);
			y = (int) (tank.getRectangle().getCenterY());
			tank.setCommand(Command.getMoveCommand(new Point(x, y)));
		}
	}

	public static void test() {
		int distance = 30;
		for (int i = 0; i < 22; i++) {
			int height = i;
			CommonTank tank = new CommonTank(GameResource.PLAY_TEAM, -20, distance, height, height, Math.PI);
			int x = (int) (tank.getRectangle().getCenterX() + 220);
			int y = (int) (tank.getRectangle().getCenterY());
			tank.setCommand(Command.getMoveCommand(new Point(x, y)));
			distance += height + 10;
		}
		distance = 30;
		for (int i = 0; i < 10; i++) {
			int height = 10 * (i + 1);
			CommonTank tank = new CommonTank(GameResource.COMP_TERM, 510, distance, height, height, 0);
			distance += height + 20;
			int x = (int) (tank.getRectangle().getCenterX() - 220);
			int y = (int) (tank.getRectangle().getCenterY());
			tank.setCommand(Command.getMoveCommand(new Point(x, y)));
		}
		// new Block(200, 200, 50, 50);
	}

	/**
	 * 取出所有与指定范围相交的实体
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static List<Entity> intersectAll(Set<Entity> excludes, int x, int y, int width, int height) {
		List<Entity> list = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isAlive()) {
				entities.remove(i);
				i--;
				continue;
			}
			if (excludes != null && excludes.contains(entity)) {
				continue;
			}
			if (entity.getRectangle().intersects(x, y, width, height)) {
				list.add(entity);
			}
		}
		return list;
	}

	public static List<Entity> intersectAll(Entity exclude, int x, int y, int width, int height) {
		List<Entity> list = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isAlive()) {
				entities.remove(i);
				i--;
				continue;
			}
			if (exclude != null && exclude == entity) {
				continue;
			}
			if (entity.getRectangle().intersects(x, y, width, height)) {
				list.add(entity);
			}
		}
		return list;
	}

	/**
	 * 取出一个与指定范围相交的实体
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static Entity intersect(Set<Entity> excludes, int x, int y, int width, int height) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isAlive()) {
				entities.remove(i);
				i--;
				continue;
			}
			if (excludes != null && excludes.contains(entity)) {
				continue;
			}
			if (entity.getRectangle().intersects(x, y, width, height)) {
				return entity;
			}
		}
		return null;
	}

	public static Entity intersect(Entity exclude, int x, int y, int width, int height) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isAlive()) {
				entities.remove(i);
				i--;
				continue;
			}
			if (exclude != null && exclude == entity) {
				continue;
			}
			if (entity.getRectangle().intersects(x, y, width, height)) {
				return entity;
			}
		}
		return null;
	}

	public static BasicTank getNearestEnemyTank(BasicTank src, int viewDis) {
		double minDistance = 0;
		BasicTank target = null;
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isAlive()) {
				entities.remove(i);
				i--;
				continue;
			}
			if (!(entity instanceof BasicTank) || src == entity) {
				continue;
			}
			BasicTank tank = (BasicTank) entity;
			if (tank.getTeam() == src.getTeam()) {
				continue;
			}
			double distance = RectangleUtil.getCenter(src.getRectangle()).distance(RectangleUtil.getCenter(tank.getRectangle())) - tank.getRectangle().width;
			if ((target == null || distance < minDistance) && distance <= viewDis) {
				target = tank;
				minDistance = distance;
			}
		}
		return target;
	}

	/**
	 * 动作
	 */
	public static void action() {
		for (int i = 0; i < painters.size(); i++) {
			Painter painter = painters.get(i);
			if (!painter.isAlive()) {
				painters.remove(i);
				i--;
				continue;
			}
			painter.action();
		}
	}

	/**
	 * 绘制
	 * 
	 * @param g
	 */
	public static void paint(Graphics g) {
		for (int i = 0; i < painters.size(); i++) {
			Painter painter = painters.get(i);
			if (!painter.isAlive()) {
				painters.remove(i);
				i--;
				continue;
			}
			painter.paint((Graphics2D) g.create());
		}
	}

	public static void add(GameObject object) {
		if (object instanceof Painter) {
			painters.add((Painter) object);
		}
		if (object instanceof Entity) {
			entities.add((Entity) object);
		}
		if (object instanceof Controller) {
			controllers.add((Controller) object);
		}
	}

	public static List<MouseAdapter> getMouseAdapters() {
		List<MouseAdapter> list = new ArrayList<MouseAdapter>();
		for (int i = 0; i < controllers.size(); i++) {
			Controller controller = controllers.get(i);
			if (!controller.isAlive()) {
				controllers.remove(i);
				i--;
				continue;
			}
			if (controller.isControlled() && controller.getMouseAdapter() != null) {
				list.add(controller.getMouseAdapter());
			}
		}
		return list;
	}

	public static List<KeyAdapter> getKeyAdapters() {
		List<KeyAdapter> list = new ArrayList<KeyAdapter>();
		for (int i = 0; i < controllers.size(); i++) {
			Controller controller = controllers.get(i);
			if (!controller.isAlive()) {
				controllers.remove(i);
				i--;
				continue;
			}
			if (controller.isControlled() && controller.getKeyAdapter() != null) {
				list.add(controller.getKeyAdapter());
			}
		}
		return list;
	}

	public static void createSelector(int x, int y) {
		Selector selector = GameResource.selector;
		if (selector != null) {
			selector.finish();
		}
		GameResource.selector = new Selector(x, y);
	}

	public static void finishSelector() {
		Selector selector = GameResource.selector;
		GameResource.selector = null;
		if (selector == null) {
			return;
		}
		for (int i = 0; i < controllers.size(); i++) {
			Controller controller = controllers.get(i);
			if (!controller.isAlive()) {
				controllers.remove(i);
				i--;
				continue;
			}
			boolean controlled = false;
			if (controller.getRectangle().intersects(selector.getRectangle()) || controller.getRectangle().contains(selector.getRectangle().getLocation())) {
				controlled = true;
				// System.out.println("选中了：" + controller);
			}
			controller.setControlled(controlled);
		}
		selector.finish();
	}

	public static void resizeSelector(int x, int y) {
		Selector selector = GameResource.selector;
		if (selector == null) {
			return;
		}
		selector.setEndPoint(x, y);
	}

	public static boolean isHpBar() {
		return hpBar;
	}

	public static boolean isViewRange() {
		return viewRange;
	}

	public static void setHpBar(boolean hpBar) {
		GameResource.hpBar = hpBar;
	}

	public static Color getTeamColor(int team) {
		switch (team) {
		case PLAY_TEAM:
			return PLAY_COLOR;
		case COMP_TERM:
			return COMP_COLOR;
		case NEUT_TEAM:
			return NEUT_COLOR;
		default:
			return OTHER_COLOR;
		}
	}
}

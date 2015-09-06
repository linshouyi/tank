package com.jesu.painter.tank;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.jesu.adapter.TankKeyAdapter;
import com.jesu.adapter.TankMouseAdapter;
import com.jesu.frame.BasicController;
import com.jesu.frame.Command;
import com.jesu.frame.Command.Option;
import com.jesu.frame.Entity;
import com.jesu.frame.GameResource;
import com.jesu.painter.HpBar;
import com.jesu.painter.weapon.Cannon;
import com.jesu.util.MathUtil;
import com.jesu.util.RectangleUtil;

public abstract class BasicTank extends BasicController {

	protected int team;// 所属阵营
	protected int hp;// 血量
	protected int fullHp;// 满血量
	protected int attack;// 攻击力
	protected int defense;// 防御力
	protected int attackDis;// 攻击距离
	protected int attackFre;// 攻击频率
	protected int speed;// 移动速度
	protected int viewDis;// 视野距离
	protected double direction;// 方向
	protected Cannon cannon;// 武器
	protected List<Command> commands = new ArrayList<Command>();// 命令集
	protected Command defaultCommand = Command.getDefenseCommand();// 默认命令

	public BasicTank(int team, int x, int y, int width, int height, int hp, int speed, double direction) {
		super(x, y, width, height);
		this.team = team;
		this.hp = this.fullHp = hp;
		this.speed = speed;
		this.direction = direction;
		this.mouseAdapter = new TankMouseAdapter(this);
		this.keyAdapter = new TankKeyAdapter(this);
		new HpBar(this);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(GameResource.getTeamColor(team));
		g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		// g.drawString(String.valueOf(rectangle.width), rectangle.x, rectangle.y);
	}

	@Override
	public void action() {
		if (!commands.isEmpty()) {// 有命令
			synchronized (commands) {
				for (int i = 0; i < commands.size(); i++) {
					boolean finish = excuteCommand(commands.get(i));
					if (finish) {
						commands.remove(i);
						i--;
					} else {
						break;
					}
				}
			}
		} else {// 没有命令,采用默认命令
			if (defaultCommand == null) {
				defaultCommand = Command.getDefenseCommand();
			}
			excuteCommand(defaultCommand);
		}
	}

	/**
	 * 执行命令
	 * 
	 * @param command
	 * @return 是否执行完成
	 */
	public boolean excuteCommand(Command command) {
		Option option = command.getOption();
		if (option == Option.MOVE) {
			if (command.getPoint() != null) {
				return move(command.getPoint());// 向目标点移动
			} else if (command.getEntity() != null) {
				return move(command.getEntity());// 向目标物移动（跟随）
			}
		} else if (option == Option.FOLLOW) {
			return move(command.getEntity());
		} else if (option == Option.ATTACK) {
			if (command.getPoint() != null) {
				return attack(command.getPoint());// 进攻目标点（冲锋）
			} else if (command.getEntity() != null) {
				return attack(command.getEntity());// 进攻目标物
			}
		} else if (option == Option.DEFENSE) {
			defense();
		} else if (option == Option.STOP) {
			return true;
		}
		return true;
	}

	/**
	 * 开火
	 */
	public void fire() {
		if (this.cannon != null && this.cannon.isAlive() && this.attackFre > 0) {
			this.attackFre--;
			this.cannon.fire();
		}
	}

	/**
	 * 侦查
	 */
	public BasicTank detect() {
		return GameResource.getNearestEnemyTank(this, this.viewDis);
	}

	/**
	 * 向目标点移动
	 * 
	 * @param x
	 * @param y
	 * @return 已经抵达目标点，执行完成
	 */
	public boolean move(Point target) {
		if (target == null) {
			return true;
		}
		double distance = getDistance(target.x, target.y);
		if (distance == 0) {
			return true;
		}
		double direction = getDirection(target.x, target.y);
		int centerX1 = 0;
		int centerY1 = 0;
		if (distance > speed) {
			centerX1 = (int) MathUtil.getCosX((int) rectangle.getCenterX(), speed, direction);
			centerY1 = (int) MathUtil.getSinY((int) rectangle.getCenterY(), speed, direction);
		} else {
			centerX1 = target.x;
			centerY1 = target.y;
		}
		int x1 = MathUtil.getTopX(centerX1, rectangle.width);
		int y1 = MathUtil.getTopY(centerY1, rectangle.height);
		if (GameResource.intersect(this, x1, y1, rectangle.width, rectangle.height) == null) {
			this.rectangle.setLocation(x1, y1);
			this.direction = direction;
		}
		return false;
	}

	/**
	 * 向目标物移动
	 * 
	 * @param entity
	 * @return 目标物已死亡，执行完成
	 */
	public boolean move(Entity entity) {
		if (entity == null || !entity.isAlive()) {
			return true;
		}
		move(RectangleUtil.getCenter(entity.getRectangle()));
		return false;
	}

	/**
	 * 向目标点进攻（侦查+进攻+移动）
	 * 
	 * @param target
	 * @return 目标点已抵达，执行完成
	 */
	public boolean attack(Point target) {
		if (target == null || isArrive(target.x, target.y)) {// 目标点为空或者已经抵达
			return true;
		}
		BasicTank enemy = detect();// 侦查敌人
		if (enemy != null) {// 有敌人
			attack(enemy);// 向敌人发起进攻
		} else {// 没有敌人
			move(target);// 向目标点移动
		}
		return false;
	}

	/**
	 * 向目标物进攻（移动+确定方向+开火）
	 * 
	 * @param entity
	 * @return 目标物已死亡，执行完成
	 */
	public boolean attack(Entity entity) {
		if (entity == null || !entity.isAlive()) {
			return true;
		}
		Point target = RectangleUtil.getCenter(entity.getRectangle());
		if (target == null) {
			return true;
		}
		double distance = getDistance(target.x, target.y);
		if (this.attackDis < distance - entity.getRectangle().width / 2) {// 攻击距离不够
			this.move(target);
		} else {
			this.direction = getDirection(target.x, target.y);
			this.fire();
		}
		return false;
	}

	/**
	 * 警戒
	 */
	public void guard() {
		BasicTank enemy = detect();
		if (enemy != null) {
			attack(enemy);
		}
	}

	/**
	 * 防御
	 */
	public void defense() {
		BasicTank enemy = GameResource.getNearestEnemyTank(this, this.attackDis);
		if(enemy!=null){
			attack(enemy);
		}
	}

	/**
	 * 受伤（遭受攻击）
	 * 
	 * @param tank
	 */
	public void hurt(BasicTank tank) {
		this.hp -= tank.attack - this.defense;
		if (this.hp <= 0) {
			this.die();
		}
	}

	/**
	 * 离目标点距离
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double getDistance(int x, int y) {
		return Point.distance(x, y, (int) rectangle.getCenterX(), (int) rectangle.getCenterY());
	}

	/**
	 * 是否已抵达目标点
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isArrive(int x, int y) {
		return x == (int) rectangle.getCenterX() && y == (int) rectangle.getCenterY();
	}

	public void addCommand(Command command) {
		this.commands.add(command);
	}

	public void setCommand(Command command) {
		synchronized (commands) {
			this.commands.clear();
		}
		this.addCommand(command);
	}

	public double getDirection(int x, int y) {
		return Math.atan2(y - (int) rectangle.getCenterY(), x - (int) rectangle.getCenterX());
	}

	public double getDirection() {
		return direction;
	}

	public int getTeam() {
		return team;
	}

	public int getHp() {
		return hp;
	}

	public int getFullHp() {
		return fullHp;
	}

	public int getAttackDis() {
		return attackDis;
	}

	public void addAttackFre() {
		this.attackFre++;
	}

	public void setViewDis(int viewDis) {
		this.viewDis = viewDis;
	}

	@Override
	public void setControlled(boolean controlled) {
		if (this.team == GameResource.PLAY_TEAM) {
			super.setControlled(controlled);
		}
	}
}

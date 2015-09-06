package com.jesu.frame;

import java.awt.Point;

/**
 * 命令(操作+目标）
 * 
 * @author linshouyi
 *
 */
public class Command {

	public enum Option {
		MOVE, // 移动：移动到目标点（右键空白处或者m+右键单位）
		ATTACK, // 进攻（移动+攻击）：主动攻击目标物或者主动攻击至目标点（右键敌方单位或者a+右键）
		FOLLOW, // 跟随：跟随目标物移动（右键我方单位或者f+右键单位）
		PATROL, // 巡逻：主动进攻当前点与目标点之间（p+右键）
		GUARD, // 警戒：主动攻击，完成后归位（g）
		DEFENSE, // 防御：原地不动，敌人进入射程后攻击（f）
		STAY, // 停留：原地不动，无任何动作（y）
		STOP;// 停止：取消所有动作（s）
	}

	private Point point;// 目标点，静态
	private Entity entity;// 目标物，动态
	private Option option;// 操作

	private Command(Option option) {
		this(option, null, null);
	}

	private Command(Option option, Point point) {
		this(option, point, null);
	}

	private Command(Option option, Entity entity) {
		this(option, null, entity);
	}

	private Command(Option option, Point point, Entity entity) {
		this.option = option;
		this.point = point;
		this.entity = entity;
	}

	public static Command getMoveCommand(Point point) {
		return new Command(Option.MOVE, point);
	}
	
	public static Command getAttackCommand(Point point) {
		return new Command(Option.ATTACK, point);
	}

	public static Command getAttackCommand(Entity entity) {
		return new Command(Option.ATTACK, entity);
	}

	public static Command getFollowCommand(Entity entity) {
		return new Command(Option.FOLLOW, entity);
	}

	public static Command getPatrolCommand() {
		return new Command(Option.PATROL);
	}

	public static Command getStayCommand() {
		return new Command(Option.STAY);
	}

	public static Command getStopCommand() {
		return new Command(Option.STOP);
	}

	public static Command getDefenseCommand() {
		return new Command(Option.DEFENSE);
	}

	public static Command getGuardCommand() {
		return new Command(Option.GUARD);
	}

	public Point getPoint() {
		return point;
	}

	public Entity getEntity() {
		return entity;
	}

	public Option getOption() {
		return option;
	}

}

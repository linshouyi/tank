package com.jesu.frame;


/**
 * 基础游戏对象
 * 
 * @author linshouyi
 *
 */
public class BasicGameObject implements GameObject {

	private boolean alive;// 是否活着

	public BasicGameObject() {
		this.alive = true;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	@Override
	public void die() {
		this.alive = false;
	}

}

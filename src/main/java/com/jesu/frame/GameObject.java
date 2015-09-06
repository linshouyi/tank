package com.jesu.frame;

/**
 * 游戏对象
 * 
 * @author linshouyi
 *
 */
public interface GameObject {

	/**
	 * 是否活着
	 * 
	 * @return
	 */
	public boolean isAlive();

	/**
	 * 死亡
	 */
	public void die();

}

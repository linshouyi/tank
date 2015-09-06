package com.jesu.frame;

import java.awt.Graphics2D;

/**
 * 绘制器（可绘制的）
 * 
 * @author linshouyi
 *
 */
public interface Painter extends GameObject {

	/**
	 * 绘制
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g);

	/**
	 * 动作
	 */
	public void action();

}

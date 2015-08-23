package com.jesu;

import java.awt.Graphics2D;

/**
 * 绘制器（可绘制的）
 * 
 * @author linshouyi
 *
 */
public interface Painter extends Life {

	/**
	 * 绘制
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g);

}

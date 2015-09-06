package com.jesu.frame;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 控制器（可控制的）
 * 
 * @author linshouyi
 *
 */
public interface Controller extends Entity {

	/**
	 * 获得鼠标监听适配器
	 * 
	 * @return
	 */
	public MouseAdapter getMouseAdapter();

	/**
	 * 获取键盘监听适配器
	 * 
	 * @return
	 */
	public KeyAdapter getKeyAdapter();

	/**
	 * 是否已控制
	 * 
	 * @return
	 */
	public boolean isControlled();

	/**
	 * 设置是否已控制
	 * 
	 * @param controlled
	 */
	public void setControlled(boolean controlled);

}

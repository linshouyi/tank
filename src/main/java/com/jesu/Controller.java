package com.jesu;

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
	public MouseAdapter getAdapter();

}

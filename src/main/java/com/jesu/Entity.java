package com.jesu;

import java.awt.Rectangle;

/**
 * 实体（占据空间的）
 * 
 * @author linshouyi
 *
 */
public interface Entity extends Painter {

	/**
	 * 获得空间范围
	 * 
	 * @return
	 */
	public Rectangle getRectangle();

}

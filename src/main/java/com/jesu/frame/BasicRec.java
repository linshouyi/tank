package com.jesu.frame;

import java.awt.Rectangle;

/**
 * 基础矩形范围
 * 
 * @author linshouyi
 *
 */
public abstract class BasicRec extends BasicPainter implements Rec {

	protected Rectangle rectangle;// 范围

	public BasicRec(int x, int y, int width, int height) {
		this.rectangle = new Rectangle(x, y, width, height);
	}

	@Override
	public Rectangle getRectangle() {
		return this.rectangle;
	}

}

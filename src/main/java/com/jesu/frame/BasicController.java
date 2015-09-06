package com.jesu.frame;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

import com.jesu.painter.Selected;

/**
 * 基础控制器
 * 
 * @author linshouyi
 *
 */
public abstract class BasicController extends BasicEntity implements Controller {

	protected MouseAdapter mouseAdapter;// 鼠标适配器
	protected KeyAdapter keyAdapter;// 键盘适配器
	protected boolean controlled;// 是否已控制

	public BasicController(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.controlled = false;
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		return this.mouseAdapter;
	}

	@Override
	public KeyAdapter getKeyAdapter() {
		return this.keyAdapter;
	}

	@Override
	public boolean isControlled() {
		return this.controlled;
	}

	@Override
	public void setControlled(boolean controlled) {
		this.controlled = controlled;
		if (this.controlled) {
			new Selected(this);
		}
	}

}

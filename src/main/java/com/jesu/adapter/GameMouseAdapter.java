package com.jesu.adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jesu.frame.GameResource;

/**
 * 游戏鼠标适配器
 * 
 * @author linshouyi
 *
 */
public class GameMouseAdapter extends MouseAdapter {

	public GameMouseAdapter() {
	}

	/**
	 * 鼠标操作，不考虑线程安全问题
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouseClicked");
		for (MouseAdapter adapter : GameResource.getMouseAdapters()) {
			adapter.mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("mousePressed");
		if (e.getButton() == 1) {
			GameResource.createSelector(e.getX(), e.getY());// 创建选择器
		}
		for (MouseAdapter adapter : GameResource.getMouseAdapters()) {
			adapter.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouseReleased");
		if (e.getButton() == 1) {
			GameResource.finishSelector();
		}
		for (MouseAdapter adapter : GameResource.getMouseAdapters()) {
			adapter.mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// for (MouseAdapter adapter : resource.getMouseAdapters()) {
		// adapter.mouseEntered(e);
		// }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// for (MouseAdapter adapter : resource.getMouseAdapters()) {
		// adapter.mouseExited(e);
		// }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MouseAdapter adapter : GameResource.getMouseAdapters()) {
			adapter.mouseMoved(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == 1) {
			GameResource.resizeSelector(e.getX(), e.getY());
		}
		for (MouseAdapter adapter : GameResource.getMouseAdapters()) {
			adapter.mouseDragged(e);
		}
	}

}

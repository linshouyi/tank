package com.jesu.adapter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.jesu.Controller;
import com.jesu.frame.Coord;
import com.jesu.frame.Resource;
import com.jesu.frame.Selector;

/**
 * 游戏鼠标适配器
 * 
 * @author linshouyi
 *
 */
public class GameMouseAdapter extends MouseAdapter {

	private Coord coord;// 当前指针坐标
	private Selector selector;// 当前选择器
	private List<Selector> selectors = new ArrayList<Selector>();// 已释放选择器
	private List<Controller> controllers = new ArrayList<Controller>();// 选中的坦克

	public GameMouseAdapter() {
		coord = new Coord();
	}

	public void paint(Graphics2D g) {
		// 右上角坐标显示
		this.coord.paint(g);
		// 选择器
		Selector selector = this.selector;
		if (selector != null) {
			selector.paint(g);
		}
		// 释放选择器
		for (int i = 0; i < selectors.size(); i++) {
			Selector pickBox = selectors.get(i);
			pickBox.action();
			if (pickBox.isLive()) {
				pickBox.paint(g);
			} else {
				selectors.remove(i);
				i--;
			}
		}
		// 选中标志
		for (Controller controller : controllers) {
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 3 }, 0));
			g.draw(controller.getRectangle());
		}
	}

	private void clearControllers() {
		System.out.println("清空：" + controllers);
		controllers.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
		for (Controller controller : controllers) {
			controller.getAdapter().mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
		if (e.getButton() == 1) {
			this.selector = new Selector(e.getPoint());
		}
		for (Controller controller : controllers) {
			controller.getAdapter().mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
		if (e.getButton() == 1) {
			Selector selector = this.selector;
			this.selector = null;
			if (selector == null) {
				return;
			}
			selectors.add(selector);// 释放选择器
			clearControllers();
			this.controllers.addAll(Resource.getResource().control(selector.getRectangle()));
		}
		for (Controller controller : controllers) {
			controller.getAdapter().mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (Controller controller : controllers) {
			controller.getAdapter().mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (Controller controller : controllers) {
			controller.getAdapter().mouseExited(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		coord.setPoint(e.getPoint());
		for (Controller controller : controllers) {
			controller.getAdapter().mouseMoved(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == 1) {
			Selector selector = this.selector;
			if (selector != null) {
				selector.setEndPoint(e.getPoint());
			}
		}
		for (Controller controller : controllers) {
			controller.getAdapter().mouseDragged(e);
		}
	}

}

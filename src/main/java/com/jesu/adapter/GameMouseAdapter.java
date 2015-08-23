package com.jesu.adapter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.jesu.Coord;
import com.jesu.Resource;
import com.jesu.Selector;
import com.jesu.tank.AkTank;

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
	private List<AkTank> tanks = new ArrayList<AkTank>();// 选中的坦克
	private Resource resource;

	public GameMouseAdapter(Resource resource) {
		this.resource = resource;
		coord = new Coord();
	}

	public void clear() {
		System.out.println("清空：" + tanks);
		tanks.clear();
	}

	public void paint(Graphics g) {
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
		Graphics2D g2d = (Graphics2D) g.create();
		for (AkTank tank : tanks) {
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 3 }, 0));
			g2d.draw(tank.getRectangle());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
		if (e.getButton() == 1) {
			this.selector = new Selector(e.getPoint());
		}
		for (AkTank tank : tanks) {
			tank.getAdapter().mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
		if (e.getButton() == 1) {
			this.clear();
			Selector selector = this.selector;
			this.selector = null;
			if (selector == null) {
				return;
			}
			selectors.add(selector);
			Rectangle rec = selector.getRectangle();
			for (AkTank tank : resource.getTanks()) {
				if (tank.getRectangle().intersects(rec) || tank.getRectangle().contains(e.getPoint())) {
					tanks.add(tank);
					System.out.println("选中了：" + tank);
				}
			}
		}
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseExited(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		coord.setPoint(e.getPoint());
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseMoved(e);
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
		for (AkTank tank : tanks) {
			tank.getAdapter().mouseDragged(e);
		}
	}

}

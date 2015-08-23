package com.jesu.adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jesu.life.tank.AkTank;

/**
 * Ak坦克适配器
 * 
 * @author linshouyi
 *
 */
public class AkTankAdapter extends MouseAdapter {

	private AkTank tank;

	public AkTankAdapter(AkTank tank) {
		this.tank = tank;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("mousePressed");
		if (e.getButton() == 3) {
			System.out.println("目标：" + e.getPoint());
			this.tank.setTarget(e.getPoint());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == 3) {
			System.out.println("目标：" + e.getPoint());
			this.tank.setTarget(e.getPoint());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}

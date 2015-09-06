package com.jesu.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jesu.frame.Command;
import com.jesu.painter.tank.BasicTank;

/**
 * 坦克键盘适配器
 * 
 * @author linshouyi
 *
 */
public class TankKeyAdapter extends KeyAdapter {

	private BasicTank tank;

	public TankKeyAdapter(BasicTank tank) {
		this.tank = tank;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("========keyPressed" + e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("========keyReleased" + e);
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {// 空格
			// this.tank.attack();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {// s
			this.tank.setCommand(Command.getStopCommand());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("========keyTyped" + e);
	}
}

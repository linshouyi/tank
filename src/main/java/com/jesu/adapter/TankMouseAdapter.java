package com.jesu.adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jesu.frame.Command;
import com.jesu.frame.Entity;
import com.jesu.frame.GameResource;
import com.jesu.painter.tank.BasicTank;

/**
 * 坦克鼠标适配器
 * 
 * @author linshouyi
 *
 */
public class TankMouseAdapter extends MouseAdapter {

	private BasicTank tank;

	public TankMouseAdapter(BasicTank tank) {
		this.tank = tank;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			Entity entity = GameResource.intersect(this.tank, e.getX(), e.getY(), 1, 1);
			if (entity != null && entity instanceof BasicTank) {// 有目标物
				BasicTank target = (BasicTank) entity;
				if (this.tank.getTeam() != target.getTeam()) {
					this.tank.setCommand(Command.getAttackCommand(entity));
				} else {
					this.tank.setCommand(Command.getFollowCommand(entity));
				}
			} else {
				this.tank.setCommand(Command.getMoveCommand(e.getPoint()));
			}
		} else if (e.getButton() == 2) {
			this.tank.setCommand(Command.getAttackCommand(e.getPoint()));
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
			// Entity entity = GameResource.intersect(this.tank, e.getX(), e.getY(), 1, 1);
			// if (entity != null && entity instanceof BasicTank) {// 有目标物
			// BasicTank target = (BasicTank) entity;
			// if (this.tank.getTeam() != target.getTeam()) {
			// this.tank.addCommand(Command.getAttackCommand(entity));
			// } else {
			// this.tank.addCommand(Command.getFollowCommand(entity));
			// }
			// } else {
			// this.tank.addCommand(Command.getMoveCommand(e.getPoint()));
			// }
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}

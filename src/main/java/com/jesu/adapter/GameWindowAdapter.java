package com.jesu.adapter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏窗口适配器
 * 
 * @author linshouyi
 *
 */
public class GameWindowAdapter extends WindowAdapter {

	// 首次setVisible(true)
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing");
		System.exit(0);
	}

	// dispose()
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed");
	}

	// 激活
	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated");
	}

	// 失活
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated");
	}

	// 缩小恢复
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified");
	}

	// 缩小
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified");
	}

	// 获得焦点
	@Override
	public void windowGainedFocus(WindowEvent e) {
		System.out.println("windowGainedFocus");
	}

	// 失去焦点
	@Override
	public void windowLostFocus(WindowEvent e) {
		System.out.println("windowLostFocus");
	}

	// 状态改变
	@Override
	public void windowStateChanged(WindowEvent e) {
		System.out.println("windowStateChanged");
	}

}

package com.jesu;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.Timer;
import java.util.TimerTask;

import com.jesu.adapter.GameMouseAdapter;
import com.jesu.adapter.GameWindowAdapter;
import com.jesu.tank.AkTank;

/**
 * 游戏窗口框架
 * 
 * @author linshouyi
 *
 */
public class GameFrame extends Frame {

	private static final long serialVersionUID = -9147978160031998181L;
	private Resource resource;
	private Timer timer;

	private GameMouseAdapter mouseController;

	public GameFrame() {
		this.addWindowAdapter(new GameWindowAdapter());
		this.addKeyListener();
		this.setBounds(100, 100, 500, 400);
		// this.setResizable(false);
		this.setTitle("坦克大战v0.01");

		init();
		this.setVisible(true);
	}

	public void init() {
		resource = Resource.getResource();
		addMouseAdapter(mouseController = new GameMouseAdapter(resource));
		for (int i = 0; i < 10; i++) {
			AkTank tank = new AkTank(50, (i + 1) * 30, 20, 20);
			resource.addTank(tank);
		}
		timer = new Timer();
		timer.schedule(new PaintTimerTask(), 0, 50);
	}

	class PaintTimerTask extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Painter painter : resource.getPainters()) {
			painter.paint(g);
		}
		if (mouseController != null) {
			mouseController.paint(g);
		}
	}

	public void addKeyListener() {
		KeyAdapter adapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed:" + e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased:" + e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("keyTyped:" + e);
			}
		};
		this.addKeyListener(adapter);
	}

	public void addMouseAdapter(MouseAdapter adapter) {
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);
		this.addMouseWheelListener(adapter);
	}

	public void addWindowAdapter(WindowAdapter adapter) {
		this.addWindowListener(adapter);
		this.addWindowFocusListener(adapter);
		this.addWindowStateListener(adapter);
	}

	public static void main(String[] args) {
		new GameFrame();
	}

}

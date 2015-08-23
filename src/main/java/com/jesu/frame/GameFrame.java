package com.jesu.frame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.Timer;
import java.util.TimerTask;

import com.jesu.adapter.GameKeyAdapter;
import com.jesu.adapter.GameMouseAdapter;
import com.jesu.adapter.GameWindowAdapter;
import com.jesu.life.block.Block;
import com.jesu.life.tank.AkTank;

/**
 * 游戏窗口框架
 * 
 * @author linshouyi
 *
 */
public class GameFrame extends Frame {

	private static final long serialVersionUID = -9147978160031998181L;
	private String title = "坦克大战";
	private String version = "0.01";
	private Resource resource;
	private Timer timer;
	private GameMouseAdapter mouseController;

	public GameFrame() {
		this.addWindowAdapter(new GameWindowAdapter());
		this.addKeyAdapter(new GameKeyAdapter());
		addMouseAdapter(mouseController = new GameMouseAdapter());
		this.setBounds(100, 100, 500, 400);
		// this.setResizable(false);
		this.setTitle(title + version);
		this.setBackground(Color.WHITE);

		init();
		this.setVisible(true);
	}

	public void init() {
		resource = Resource.getResource();
		for (int i = 0; i < 10; i++) {
			resource.addTank(new AkTank(50, (i + 1) * 30, 20, 20));
		}
		resource.addBlock(new Block(100, 150, 30, 80));
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
		resource.paint(g);
		if (mouseController != null) {
			mouseController.paint((Graphics2D) g.create());
		}
	}

	public void addKeyAdapter(KeyAdapter adapter) {
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

package com.jesu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

import com.jesu.adapter.GameKeyAdapter;
import com.jesu.adapter.GameMouseAdapter;
import com.jesu.adapter.GameWindowAdapter;

/**
 * 游戏窗口框架
 * 
 * @author linshouyi
 *
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = -9147978160031998181L;
	private Image image;

	public GameFrame() {
		this.addWindowAdapter(new GameWindowAdapter());
		this.addKeyAdapter(new GameKeyAdapter());
		this.addMouseAdapter(new GameMouseAdapter());
		this.setBounds(0, 0, 1500, 800);
		// this.setResizable(false);
		this.setTitle("坦克大战0.01");
		// this.setRootPane(null);
		System.out.println(this.getBackground());
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// long start = System.currentTimeMillis();
		if (image == null) {
			image = this.createImage(this.getWidth(), this.getHeight());
		}
		Graphics ig = image.getGraphics();
		ig.setColor(Color.WHITE);
		ig.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		// super.paint(ig);
		GameResource.paint(ig);
		g.drawImage(image, 0, 0, null);
		// System.out.println("===============" + (System.currentTimeMillis() - start));
	}

	@Override
	public void update(Graphics g) {
		// super.update(g);
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
}

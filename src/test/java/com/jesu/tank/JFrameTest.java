package com.jesu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class JFrameTest extends JFrame {

	private static final long serialVersionUID = -1230964314149828184L;
	private Image image;
	private int step;

	public JFrameTest() {
		this.setBackground(Color.RED);
		// this.getContentPane().setBackground(Color.GREEN);
		this.getMenuBar();
		this.getGlassPane();
		this.getLayeredPane();
		this.getRootPane();
		this.setBounds(100, 100, 500, 500);
		// this.add(new Button("ok"));
		// LayoutManager layout = this.getLayout();
		try {
			long start = System.currentTimeMillis();
			image = ImageIO.read(new File("弓兵.png"));
			System.out.println("=========" + (System.currentTimeMillis() - start));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (image == null) {
			return;
		}
		try {
			int x = 100;
			int y = 100;
			int width = image.getWidth(null) / 3;
			int height = image.getHeight(null) / 4;

			int index = step++ % 3;
			int x1 = index * width;
			int y1 = height;
			int width1 = width;
			int height1 = height;
			long start = System.currentTimeMillis();
			System.out.println(g.drawImage(image, x, y, x + width, y + height, x1, y1, width1, height1, Color.RED, null));
			System.out.println("====" + (System.currentTimeMillis() - start));
			// g.drawImage(image, 0, 0, 100, 100, Color.RED, null);
			// g.drawImage(ImageIO.read(new File("弓兵.png")), 0, 0, 100, 100, 0, 0, 100, 100, Color.RED, null);
			// BufferedImage image = ImageIO.read(new File("tankR.gif"));
			// g.drawImage(image, 50, 50, null);
			// g.drawImage(transform(image, 1), 100, 50, Color.RED, null);
			// g.drawImage(transform(image, 2), 150, 50, null);
			// g.drawImage(transform(image, 3), 200, 50, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Image transform(BufferedImage image, int type) {
		AffineTransform transform = null;
		if (type == 1) {
			transform = new AffineTransform(-1, 0, 0, 1, image.getWidth() - 1, 0);// 水平翻转
		} else if (type == 2) {
			transform = new AffineTransform(1, 0, 0, -1, 0, image.getHeight() - 1);// 垂直翻转
		} else if (type == 3) {
			transform = new AffineTransform(-1, 0, 0, -1, image.getWidth() - 1, image.getHeight() - 1);// 旋转180度
		}
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}

	public static void main(String[] args) {
		final JFrame jframe = new JFrameTest();
		Timer timer = new Timer();// 时间轴
		long period = 100;// 每帧时间
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				jframe.repaint();// 每帧重绘页面，刷新页面
			}
		}, 0, period);
	}
}

package com.jesu.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

public class GameFrameTest extends Frame {

	private static final long serialVersionUID = 1L;

	public GameFrameTest() {
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		// Image image = this.createImage(100, 100);
		// Graphics ig = image.getGraphics();
		// Graphics2D g2d = (Graphics2D) g.create();
		// // Rectangle rectangle = new Rectangle(200, 114, 10, 280);
		// // g2d.setColor(Color.BLUE);
		// // g2d.draw(rectangle);
		// int[] xpoints = { 0, 0, 200, 200, 100 };
		// int[] ypoints = { 0, 200, 200, 30, 50 };
		// Polygon polygon = new Polygon(xpoints, ypoints, xpoints.length);
		// polygon.translate(100, 100);
		// g2d.setColor(Color.RED);
		//
		// Area area = new Area(polygon);
		// // area.subtract(new Area(rectangle));
		// // area.add(new Area(rectangle));
		// area.intersect(new Area(polygon));
		// g2d.draw(area);

		// g2d.setColor(Color.BLACK);
		// g2d.draw(polygon.getBounds());

		// System.out.println(polygon.contains(rectangle));
		// System.out.println(polygon.intersects(rectangle));

		// Point point = new Point(100, 100);

		// System.out.println("getBounds:"+polygon.getBounds());
		// System.out.println("getBounds2D:"+polygon.getBounds2D());

		// System.out.println("contains：" + polygon.contains(point));
		// Area area = new Area();
		// area.add(rhs);
		// g2d.setColor(Color.RED);
		// g2d.draw(polygon.getBounds());

		// g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] { 1, 10 }, 0));
		// g2d.drawLine(point.x, point.y, point.x, point.y);
	}

	public static void main(String[] args) {
		Frame f = new GameFrameTest();
		Panel p = new Panel();
		p.setBackground(Color.yellow);
		// p.setVisible(true);
		p.setSize(30, 30);
		p.setBounds(20, 20, 20, 20);
		f.setBackground(Color.GREEN);
		f.setBounds(0, 0, 300, 300);
		f.setVisible(true);
	}
}

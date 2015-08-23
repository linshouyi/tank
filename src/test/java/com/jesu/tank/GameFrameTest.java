package com.jesu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;

import com.jesu.frame.GameFrame;

public class GameFrameTest extends GameFrame {

	private static final long serialVersionUID = 1L;

	public static void mathTest() {
		System.out.println(Math.pow(5, 0.5));
		System.out.println(Math.pow(2.23606797749979, 2));
		System.out.println(Math.pow(2, 0.5));
		System.out.println(Math.log10(1.4142135623730951) / Math.log10(2));
		int x = 10;
		int y = 10;
		int targetX = 13;
		int targetY = 14;
		double a = Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2);
		System.out.println(a);
		double distance = Math.pow(a, 0.5);
		System.out.println(distance);
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		Graphics2D g2d = (Graphics2D) g.create();
		// Rectangle rectangle = new Rectangle(200, 114, 10, 280);
		// g2d.setColor(Color.BLUE);
		// g2d.draw(rectangle);

		int[] xpoints = { 0, 0, 200, 200, 100 };
		int[] ypoints = { 0, 200, 200, 30, 50 };
		Polygon polygon = new Polygon(xpoints, ypoints, xpoints.length);
		polygon.translate(100, 100);
		g2d.setColor(Color.RED);

		Area area = new Area(polygon);
		// area.subtract(new Area(rectangle));
		// area.add(new Area(rectangle));
		area.intersect(new Area(polygon));
		g2d.draw(area);

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
		new GameFrameTest();
	}
}

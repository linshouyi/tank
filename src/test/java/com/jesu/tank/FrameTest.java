package com.jesu.tank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrameTest extends Frame {

	private static final long serialVersionUID = 1L;

	public void test() {
		// Panel p = new Panel();
		// Panel p1 = new Panel();
		// Panel p2 = new Panel();
		// p.setBackground(Color.RED);
		// p1.setBackground(Color.YELLOW);
		// p2.setBackground(Color.GRAY);
		// add(p);
		// add(p1);
		// add(p2);

		setLayout(new BorderLayout());
		Button b1 = new Button("ok1");
		Button b2 = new Button("ok2");
		Button b3 = new Button("ok3");
		Button b4 = new Button("ok4");
		Button b5 = new Button("ok5");

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
			}
		};
		b1.addActionListener(al);
		b2.addActionListener(al);
		b3.addActionListener(al);
		b4.addActionListener(al);
		b5.addActionListener(al);

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		TextField tf = new TextField(50);
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(e.getActionCommand());
				System.out.println(e.toString());
				TextField o = (TextField) e.getSource();
				o.setText("");
				System.out.println("==");
			}
		});
		add(tf);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		// add(b1, BorderLayout.EAST);
		// add(b2, BorderLayout.SOUTH);
		// add(b3, BorderLayout.NORTH);
		// add(b4, BorderLayout.WEST);
		// add(b5, BorderLayout.CENTER);
		// setLayout(new GridLayout(5,2));
		// add(new Button("ok1"));
		// add(new Button("ok2"));
		// add(new Button("ok3"));
		// add(new Button("ok4"), 2);
		// add(new Button("ok5"));
		// add(new Button("ok6"));
		// add(new Button("ok7"));
	}

	public void test2() {
		final TextField tf1 = new TextField(10);
		final TextField tf2 = new TextField(10);
		final TextField tf3 = new TextField(10);
		Label l = new Label("+");
		Button btn = new Button("=");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(tf1);
		add(l);
		add(tf2);
		add(btn);
		add(tf3);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = Integer.parseInt(tf1.getText()) + Integer.parseInt(tf2.getText());
				tf3.setText(String.valueOf(result));
			}
		});
	}

	private Frame frame = this;

	public void test3() {
		Button btn = new Button("登录");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame f = null;
				Dialog dialog = new Dialog(f);
				dialog.setVisible(true);
				dialog.setBounds(100, 100, 100, 100);
				System.out.println(dialog);
			}
		});
		this.add(btn);
	}

	public FrameTest() {
		// test3();
		pack();
		setBackground(Color.GREEN);
		setBounds(100, 100, 500, 500);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		// try {
		// Image img = ImageIO.read(new File("弓兵.png"));
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("0028.png");
		g.drawImage(img, 100, 100, Color.BLACK, null);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) {
		new FrameTest();
	}
}

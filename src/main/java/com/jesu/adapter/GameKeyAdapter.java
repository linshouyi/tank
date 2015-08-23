package com.jesu.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyAdapter extends KeyAdapter {

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

}

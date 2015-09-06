package com.jesu.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jesu.frame.GameResource;

public class GameKeyAdapter extends KeyAdapter {

	public GameKeyAdapter() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed:" + e);
		if (e.getKeyCode() == KeyEvent.VK_ALT) {// alt
			GameResource.setHpBar(!GameResource.isHpBar());
		}
		for (KeyAdapter adapter : GameResource.getKeyAdapters()) {
			adapter.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("keyReleased:" + e);
		if (e.getKeyCode() == KeyEvent.VK_1) {// 1
			GameResource.createPlayTanks();
		} else if (e.getKeyCode() == KeyEvent.VK_2) {// 2
			GameResource.createCompTanks();
		}
		for (KeyAdapter adapter : GameResource.getKeyAdapters()) {
			adapter.keyReleased(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("keyTyped:" + e);
		for (KeyAdapter adapter : GameResource.getKeyAdapters()) {
			adapter.keyTyped(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(0x53);
	}
}

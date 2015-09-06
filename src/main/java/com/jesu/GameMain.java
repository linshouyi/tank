package com.jesu;

import java.util.Timer;
import java.util.TimerTask;

import com.jesu.frame.GameFrame;
import com.jesu.frame.GameResource;

public class GameMain {

	public static void main(String[] args) {
		GameResource.createPlayTanks();
		GameResource.createCompTanks();
		final GameFrame frame = new GameFrame();
		Timer timer = new Timer();// 时间轴
		long period = 40;// 人眼最低频率24帧/秒
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				GameResource.action();
				frame.repaint();// 每帧重绘页面，刷新页面
			}
		}, 0, period);
	}
}

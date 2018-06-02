package com.neusoft.planewar.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neusoft.planewar.constant.Constant;

public abstract class Myframe extends Frame {
	public boolean isPause = false;

	public void loadFrame() {
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Billiards");
		setVisible(true);
		setResizable(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			

		});
		new MyThread().start();
	}

	Image backImg = GameUtil.getImageJpg("background/img_bg_level_2");

	public abstract void paint(Graphics g);
	public abstract void paint2(Graphics g);

	public void update(Graphics g) {
		
		if (!isPause) {

			if (backImg == null) {
				backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			}
			Graphics backg = backImg.getGraphics();
			Color c = backg.getColor();
			backg.setColor(Color.WHITE);
			backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			backg.setColor(c);
			paint(backg);
			g.drawImage(backImg, 0, 0, null);
		} else {	
			//加载游戏失败图片
			if (backImg == null) {
				backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			}
			Graphics backg = backImg.getGraphics();
			Color c = backg.getColor();
			backg.setColor(Color.WHITE);
			backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			backg.setColor(c);
			paint2(backg);
			g.drawImage(backImg, 0, 0, null);
		}

	}

	

	class MyThread extends Thread {
		public void run() {
			while (true) {
				repaint();// 继承于Frame,调用paint()函数
				try {
					Thread.sleep(50);// 人眼可以辨识的范围大概40-50，需要try
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

	}
}

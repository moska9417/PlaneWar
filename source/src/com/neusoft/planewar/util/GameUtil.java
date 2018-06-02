package com.neusoft.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil {
	public static Image getImagePng(String imgPath) {
		URL u = GameUtil.class.getClassLoader().getResource("com/neusoft/planewar/image/" + imgPath + ".png");
		BufferedImage img = null;

		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public static Image getImageJpg(String imgPath) {
		URL u = GameUtil.class.getClassLoader().getResource("com/neusoft/planewar/image/" + imgPath + ".jpg");
		BufferedImage img = null;

		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}

package com.neusoft.planewar.abstracts;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.interfaces.Drawable;
import com.neusoft.planewar.interfaces.Hitable;
import com.neusoft.planewar.interfaces.Moveable;

public abstract class PlaneWarObject implements Drawable, Moveable {

	public int x;
	public int y;
	public Image img;
	public int speed;
	public PlaneWarClient pwc;
	public boolean isPlayer;
	
	

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		
		move();

	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}

	
	@Override
	public abstract void move();

}

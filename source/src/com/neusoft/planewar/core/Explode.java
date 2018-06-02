package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.abstracts.PlaneWarObject;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.util.Images;

public class Explode extends PlaneWarObject {

	int count = 0;
	public static Image[] imgs=  new Image[16];
	static {
		for(int i=1;i<=16;i++) {
			imgs[i-1]=Images.get("explode"+i);
		}
	}
	public Explode() {
		
		// TODO Auto-generated constructor stub
	}
	public Explode(PlaneWarClient pwc ,int x,int y) {
		this.pwc=pwc;
		this.x=x;
		this.y=y;
		
	}
	public void draw(Graphics g) {
		if(count==15) {
			pwc.explodes.remove(this);
			return ;
		}
		g.drawImage(imgs[++count], x, y,150,150,null);
		 
		
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}

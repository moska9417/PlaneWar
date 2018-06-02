package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Console;

import com.neusoft.planewar.abstracts.PlaneWarObject;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.Images;

public  class Item extends PlaneWarObject {
	boolean live=true;
	public int score=10; 
	public int num=0;
	public boolean magnet=false;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Item() {
		
	}
	
	public Item(PlaneWarClient pwc,int x,int y,Image image) {
		
		this.pwc=pwc;
		this.x=x;
		this.y=y;
		this.img=image;
		this.speed=-7;
		//System.out.println(image.getWidth(null));
	}
	public Item(PlaneWarClient pwc, int x, int y, int next) {
		// TODO Auto-generated constructor stub
		this.pwc=pwc;
		this.x=x;
		this.y=y;
		this.num=next;
		this.img=Images.get("item"+next);
		this.speed=-7;
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(!live) {
			pwc.items.remove(this);
		}
		super.draw(g);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub]
		if(y>Constant.GAME_HEIGHT) {
			pwc.items.remove(this);
		}
		if(magnet) {
			x+=(x<this.pwc.player01.x)?10:x==0?0:-10;
			y+=(y<this.pwc.player01.y)?10:y==0?0:-10;
			//System.out.println("a");
		}else {
			changespeed();
			y+=speed;
			
		}
	}

	protected void changespeed() {
		// TODO Auto-generated method stub
		speed+=1;
	}

	public void hitPlayer(PlayerPlane player01) {
		// TODO Auto-generated method stub
		if(this.getRectangle().intersects(player01.getRectangle())) {
			switch (num) {
			case 1:
				break;
			case 2:
			case 3:
			case 4:
			case 12:
				score=20;
				break;
			case 5:
				break;
			case 6:
				player01.setHealth(player01.getHealth()+10);
				break;
			case 7:
				player01.lightning();
				break;
			case 8:
				player01.setMagnet();
				break;
				
			case 9:
				player01.MaxPower();
				break;
			case 10:
				player01.bomb++;
				break;
			case 11:
				player01.QuintuplePower();
				break;
			case 13:
				player01.setspeedup();
			case 14:
				player01.TripleArrow();
				
				
			default:
				break;
			}
			pwc.items.remove(this);
			player01.setScore(player01.getScores()+this.score);
			
		}
	}

	
}

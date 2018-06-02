package com.neusoft.planewar.abstracts;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Direction;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.util.GameUtil;

public abstract class Plane extends PlaneWarObject{
	
	
	
	int times=0;
	int newWidth;
	int previousWidth;
	public boolean fires;
	public boolean left, up, right, down;

	private int imgnum = 1;
	private boolean live = true;
	public Direction dir = Direction.STOP;
	
	
	

	//getter and setter
	public int getImgnum() {
		return imgnum;
	}
	public void setImgnum(int imgnum) {
		this.imgnum = imgnum;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
	//Constractor
	public Plane(){
		
	}
	public Plane(PlaneWarClient pwc,int x,int y) {
		this.x=x;
		this.y=y;
		this.pwc=pwc;
		this.speed=10;
		
	}
	public Plane(PlaneWarClient pwc,int x,int y,Image pngImg ) {
		this(pwc,x,y);
		this.img=pngImg;
		
	 
	}
	public Plane(PlaneWarClient pwc,int x,int y,String  pngStr ) {
		this(pwc,x,y);
		this.img=GameUtil.getImagePng(pngStr);
		
	}
	//function
	public void Explode(PlaneWarClient pwc, int x, int y) {
    	Explode e = new Explode(pwc,x,y);
    	pwc.explodes.add(e);
    }
	public void reverseImage(String skin){
		if(times%2==0) {
			times=0;
			try {
				previousWidth=GameUtil.getImagePng(skin+"/"+getImgnum()).getWidth(null);
				setImgnum(getImgnum()+1);
				newWidth=GameUtil.getImagePng(skin+"/"+getImgnum()).getWidth(null);
				x=x+previousWidth/2-newWidth/2;
				img=GameUtil.getImagePng(skin+"/"+getImgnum());
				
			}catch (Exception e) {
				setImgnum(1);
				newWidth=GameUtil.getImagePng(skin+"/"+getImgnum()).getWidth(null);
				x=x+previousWidth/2-newWidth/2;
				img=GameUtil.getImagePng(skin+"/"+getImgnum());
			}
			
		}times++;
		
	}
	@Override
	public void move() {
		
		switch (dir) {
		case LEFT:
			x -= speed;
			break;
		case LEFT_UP:
			x -= speed;
			y -= speed;
			break;
		case UP:
			y -= speed;
			break;
		case RIGHT_UP:
			x += speed;
			y -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		case RIGHT_DOWN:
			x += speed;
			y += speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT_DOWN:
			x -= speed;
			y += speed;
			break;
		default:
			break;

		}

		if (fires == true)
			fire();

		if (x < 0)
			x += speed;
		if (y < 25)
			y += speed;
		if (x > Constant.GAME_WIDTH - this.img.getWidth(null))
			x -= speed;
		if (y > Constant.GAME_HEIGHT - this.img.getHeight(null))
			y -= speed;

	}
	public void draw(Graphics g) {
		if (!live) {
			pwc.enemies.remove(this);
		}
		super.draw(g);
	}
	public void configmDirection() {
		if (left && !up && !right && !down) {
			dir = Direction.LEFT;
		} else if (left && up && !right && !down) {
			dir = Direction.LEFT_UP;
		} else if (!left && up && !right && !down) {
			dir = Direction.UP;
		} else if (!left && up && right && !down) {
			dir = Direction.RIGHT_UP;
		} else if (!left && !up && right && !down) {
			dir = Direction.RIGHT;
		} else if (!left && !up && right && down) {
			dir = Direction.RIGHT_DOWN;
		} else if (!left && !up && !right && down) {
			dir = Direction.DOWN;
		} else if (left && !up && !right && down) {
			dir = Direction.LEFT_DOWN;
		} else {
			dir = Direction.STOP;
		}

	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_A:
			fires = true;
		case KeyEvent.VK_SPACE:
			
		default:
			break;

		}

		configmDirection();
		
	}
	
	public void keyReleased(KeyEvent e) {

		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_A:
			fires = false;
		default:
			break;
		}
		configmDirection();
		
	}

	//abstract function
	public abstract void fire(); 
}




















package com.neusoft.planewar.abstracts;

import java.awt.Rectangle;
import java.util.List;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Direction;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.util.GameUtil;

public abstract class Bullet extends PlaneWarObject{
	
	public Direction dir=Direction.UP;
	//Constractor
	public Bullet(){
        
    }
	public Bullet(PlaneWarClient pwc,int x,int y,String imgName){
    	this.pwc=pwc;	
        this.x=x;
        this.y=y;
        this.img=GameUtil.getImagePng(imgName);
        this.speed =40;
        
    }
	//function
	public void move(){
    	
    	switch(dir){
        case LEFT:
        	x-=speed;
        	break;
        case LEFT_UP:
        	x-=speed;
        	y-=speed;
        	break;
        case UP:
        	
        	y-=speed;
        	break;
        case RIGHT_UP:
        	x+=speed;
        	y-=speed;
        	break;
        case RIGHT:
        	x+=speed;
        	break;
        case RIGHT_DOWN:
        	x+=speed;
        	y+=speed;
        	break;
        case DOWN:
        	y+=speed;
        	break;
        case LEFT_DOWN:
        	x-=speed;
        	y+=speed;
        	break;
        default:
        	break;
    	}
    	if(x<0||y<0||x>Constant.GAME_WIDTH+500||y>Constant.GAME_HEIGHT+500) {
    		pwc.bulletOfPlayer.remove(this);
    	}
    	
    }
	public void Exlode(PlaneWarClient pwc, int x, int y) {
    	Explode e = new Explode(pwc,x,y);
    	pwc.explodes.add(e);
    }
	//abstract function
	public abstract boolean  hitPlane(Plane p);
	public abstract boolean hitPlane(List<? extends Plane> enemies);
}

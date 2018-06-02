package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import com.neusoft.planewar.abstracts.Bullet;
import com.neusoft.planewar.abstracts.Plane;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Direction;
import com.neusoft.planewar.core.item.ItemXing;
import com.neusoft.planewar.util.Images;

public class PlayerBullet extends Bullet {
	int level=1;
	int times=0;
		
	public PlayerBullet(PlaneWarClient pwc,int x,int y,String imgName) {
		// TODO Auto-generated constructor stub
		super(pwc,x,y,imgName);
		this.dir = Direction.UP;
	}
	public PlayerBullet(PlaneWarClient pwc,int x,int y,String imgName,Direction dr) {
		// TODO Auto-generated constructor stub
		super(pwc,x,y,imgName);
		this.dir = dr;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		switch (pwc.player01.level) {
		case 1:
			g.drawImage(img, x, y, null);
			move();
			break;
		case 2:
			
			g.drawImage(img, x, y, null);
			g.drawImage(img, x-(speed-2)*(times), y, null);
			g.drawImage(img, x+(speed-2)*(times), y, null);
			move();
		default:
			g.drawImage(img, x, y, null);
			g.drawImage(img, x-speed*(times%2), y, null);
			g.drawImage(img, x+speed*(times%2), y, null);
			move();
			break;
		}
		times++;
	}
	public boolean hitPlane(Plane plane){//≈–∂œœ‡Ωª
		EnemyPlane p=(EnemyPlane)plane;
        if(this.getRectangle().intersects(p.getRectangle())&&!p.isPlayer&&p.isLive()){
        	//System.out.println(this.x+" "+this.y+" "+p.x+" "+p.y);
        	pwc.player01.setExp(p.exp);
        	pwc.bulletOfPlayer.remove(this);
        	
        	Random r=new Random();
        	
        	int itemSum=r.nextInt(2);
        	for(int i=0;i<itemSum;i++){
        		Item e=new Item(this.pwc,x,y,r.nextInt(14)+1);
        		pwc.items.add(e);
        	}
        	itemSum=r.nextInt(2);
        	for(int i=0;i<itemSum;i++){
        		ItemXing e=new ItemXing(this.pwc,x,y);
        		pwc.items.add(e);
        		
        	}
        	
        	Exlode(pwc,this.x-75,this.y-75);
        	
        		return true;
        }
        
        return false;
    }
	@Override
	public boolean hitPlane(List<? extends Plane> enemies) {
		for(int i=0;i<enemies.size();i++){
            EnemyPlane enemy =(EnemyPlane) enemies.get(i);
            if(this.hitPlane(enemy)){
            	this.pwc.enemies.get(i).setLive(false);
            	
                return true;
            }
        }
            return false;
	}
   
}

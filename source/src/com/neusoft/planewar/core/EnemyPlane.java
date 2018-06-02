package com.neusoft.planewar.core;

import java.util.List;
import java.util.Random;

import com.neusoft.planewar.abstracts.Plane;
import com.neusoft.planewar.abstracts.PlaneWarObject;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.interfaces.Hitable;
import com.neusoft.planewar.util.Images;

public class EnemyPlane extends Plane implements Hitable {
	int Health=20;
	int  exp=10;
	public EnemyPlane(PlaneWarClient pwc, int x, int y, String string) {
		// TODO Auto-generated constructor stub
		super(pwc, x, y, string);
		speed = 3;
		exp=10;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		 y+=1;
		 
		Random r = new Random();
		int f=r.nextInt(1000);
		if (f >990) {
			fire();
			
		}
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		int i = (int) (Math.random() * 10);
		EnemyBullet bs = new EnemyBullet(pwc,
				x + this.img.getWidth(null) / 2 - Images.get("bullet000fE").getWidth(null) / 2,y - Images.get("bullet000fE").getHeight(null) / 2 , "Bullet/000fE");// 得到子弹的高度Images.get("").getHeight(null)
		this.pwc.bulletOfEnemy.add(bs);
	}
	@Override
	public boolean isHit(PlaneWarObject object) {
		PlayerPlane pp=(PlayerPlane)object;
		if(this.getRectangle().intersects(pp.getRectangle())&&pp.isPlayer&&isLive()) {
			Explode(pwc,pp.x-75,pp.y-75);
			pp.setLive(false);
		}
		return false;
	}

	
	


}

package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import com.neusoft.planewar.abstracts.Bullet;
import com.neusoft.planewar.abstracts.Plane;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Direction;
import com.neusoft.planewar.util.Images;

public class EnemyBullet extends Bullet {
	int demage = 10;
	int times = 1;

	public EnemyBullet(PlaneWarClient pwc, int x, int y, String imgName) {
		// TODO Auto-generated constructor stub
		super(pwc, x, y, imgName);
		this.dir = Direction.DOWN;
		this.x = this.x + -this.img.getWidth(null) / 2;
		this.y = this.y + this.img.getHeight(null);

	}

	@Override
	public boolean hitPlane(Plane p) {
		PlayerPlane player=(PlayerPlane)p;
		// TODO Auto-generated method stub
		if (this.getRectangle().intersects(p.getRectangle()) && p.isPlayer&&p.isLive()) {
			pwc.bulletOfEnemy.remove(this);
			if(player.getHealth()-demage>0) {
				player.setHealth(player.getHealth()-demage);
				
			}else {
				
				p.setLive(false);
				
				
			}
			Exlode(pwc,this.x-75,this.y-75);
			return true;
		}

		return false;
	}

	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		y += 10;
		
		if(y>Constant.GAME_HEIGHT) {
			System.out.println(y);
			pwc.bulletOfEnemy.remove(this);
		}
		
	}

	@Override
	public boolean hitPlane(List<? extends Plane> enemies) {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemies.size(); i++) {
			PlayerPlane enemy = (PlayerPlane) enemies.get(i);
			if (this.hitPlane(enemy)) {
				this.pwc.enemies.get(i).setLive(false);

				return true;
			}
		}
		return false;
	}


	

}

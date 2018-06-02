package com.neusoft.planewar.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.neusoft.planewar.abstracts.Plane;
import com.neusoft.planewar.abstracts.PlaneWarObject;
import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.interfaces.Hitable;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.Images;

public class PlayerPlane extends Plane implements Hitable{
	int magnetTimer=0;
	int MaxPowerTimer=0;
	public int speedPowerTimer=0;
	int level=1;
	int exp=0;
	public int bomb=0;
	public int tribleBulletTimer=0;
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = this.exp+exp;
		if(this.exp<100)level=1;
		else if(this.exp<200)level=2;
		else if(this.exp<500)level=3;
		else if(this.exp<1000)level=4;
		else if(this.exp<1500)level=5;
	}
	String skin;
	public int score=0;
	private static int  Health = 100;
	
	
	public int getHealth() {
		return Health;
	}
	public int [] getHealths() {
		
		int[] y = new int[String.valueOf(Health).length()];
		for(int i=0;i<y.length-1;i++) {
			y[i]=score/(int)Math.pow(10, y.length-1);
		}
		y[y.length-1]=score%10;
		return y;
	}
	public void setHealth(int health) {
		Health = health;
	}
	public int[]  getScore() {
		 int[] y = new int[String.valueOf(score).length()];
         for(int i=0;i<y.length-1;i++) {
        	 y[i]=score/(int)Math.pow(10, y.length-1);
         }
         y[y.length-1]=score%10;
		return y;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public PlayerPlane(PlaneWarClient pwc,int x,int y,String skin) {
		// TODO Auto-generated method stub
		super(pwc,x,y);
		this.speed=20;
		this.skin=skin;
		this.img = GameUtil.getImagePng(skin+"/1");
		this.x=this.x-this.img.getWidth(null)/2;
		isPlayer=true;
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(isLive()) {
			//System.out.println(level);
			super.draw(g);
			reverseImage(skin);
			//if(magnetTimer>0)magnet();
			if(speedPowerTimer>0)speedup();
			
		}else {
			//我方战机死亡，游戏暂停
			pwc.isPause=true;
			
		}
		
	}
	@Override
	public void fire() {
		PlayerBullet bs;
//		if(tribleBulletTimer>0) {
//			bs= new PlayerBullet(pwc,
//					x + this.img.getWidth(null) / 2 - Images.get("bullet36").getWidth(null) / 2,
//					y - Images.get("bullet36").getHeight(null) / 2 +(int) (Math.random() * 10), "Bullet/36");
//			this.pwc.bulletTribleOfPlayer.add(bs);
//			this.pwc.bulletTribleOfPlayer.add(bs);
//			this.pwc.bulletTribleOfPlayer.add(bs);
//			return ;
//		}
//		System.out.println("tribleBulletTimer"+tribleBulletTimer);
		// TODO Auto-generated method stub
		switch(level) {
		case 1:
			int i = (int) (Math.random() * 10);
			 bs = new PlayerBullet(pwc,
					x + this.img.getWidth(null) / 2 - Images.get("bullet000f").getWidth(null) / 2,
					y - Images.get("bullet000f").getHeight(null) / 2 + i, "Bullet/000f");// 得到子弹的高度Images.get("").getHeight(null)
			// System.out.println(i);
			this.pwc.bulletOfPlayer.add(bs);
			break;
		case 2:
			bs = new PlayerBullet(pwc,
					x + this.img.getWidth(null) / 2 - Images.get("bullet38").getWidth(null) / 2,
					y - Images.get("bullet38").getHeight(null) / 2 +(int) (Math.random() * 10), "Bullet/38");// 得到子弹的高度Images.get("").getHeight(null)
			// System.out.println(i);
			this.pwc.bulletOfPlayer.add(bs);
			
		break;
		default:
			bs = new PlayerBullet(pwc,
					x + this.img.getWidth(null) / 2 - Images.get("bullet009").getWidth(null) / 2,
					y - Images.get("bullet009").getHeight(null) / 2 +(int) (Math.random() * 10), "Bullet/009");// 得到子弹的高度Images.get("").getHeight(null)
			// System.out.println(i);
			this.pwc.bulletOfPlayer.add(bs);
				break;
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			N_bomb();
		}
	}

	@Override
	public boolean isHit(PlaneWarObject object) {
		// TODO Auto-generated method stub
		
		return false;
	}
	public int getScores() {
		// TODO Auto-generated method stub
		return score;
	}
	
	public void lightning() {
		// TODO Auto-generated method stub
		
	}
	public void setMagnet() {
		magnetTimer=1000;
		for(int i=0;i<pwc.items.size();i++) {
			pwc.items.get(i).magnet=true;
		}
		for(int i=0;i<pwc.ix.size();i++) {
			pwc.ix.get(i).magnet=true;
		}
	}
	public void magnet() {
		// TODO Auto-generated method stub
		magnetTimer--;
		//System.out.println(magnetTimer);
		
		
	}
	public void MaxPower() {
		// TODO Auto-generated method stub
		
	}
	public void N_bomb() {
		// TODO Auto-generated method stub
		if(bomb>=0) {
			for(int i=0;i<pwc.enemies.size();i++) {
				pwc.enemies.get(i).setLive(false);
				pwc.explodes.add(new Explode(pwc,pwc.enemies.get(i).x,pwc.enemies.get(i).y));
			}
			bomb--;
		}
	}
	public void QuintuplePower() {
		// TODO Auto-generated method stub
		
	}
	public void speedup() {
		// TODO Auto-generated method stub
		speedPowerTimer--;
		System.out.println(speedPowerTimer);
		
	}
	public void TripleArrow() {
		tribleBulletTimer=2000;
		// TODO Auto-generated method stub
		
	}
	public void setspeedup() {
		// TODO Auto-generated method stub
		speedPowerTimer=100;
		
	}
}

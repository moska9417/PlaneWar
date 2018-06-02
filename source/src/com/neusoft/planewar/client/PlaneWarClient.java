package com.neusoft.planewar.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.ws.handler.MessageContext.Scope;

import com.neusoft.planewar.abstracts.Bullet;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.constant.Direction;
import com.neusoft.planewar.core.EnemyBullet;
import com.neusoft.planewar.core.PlayerBullet;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.core.Item;
import com.neusoft.planewar.core.MusicThread;
import com.neusoft.planewar.core.EnemyPlane;
import com.neusoft.planewar.core.PlayerPlane;
import com.neusoft.planewar.core.item.ItemXing;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.Images;
import com.neusoft.planewar.util.Myframe;

import javazoom.jl.decoder.JavaLayerException;

public class PlaneWarClient extends Myframe {
	 //��ʼ
		public boolean start=false;
		public boolean help=false;

	int lightningx=0;
	// ��ʱ��
	int k = 0;

	// ����
	Image bg = GameUtil.getImageJpg("background/img_bg_level_2");
	public int bgspeed = 6;
	public int bgy = 0;
	public int times = 0;

	// item
	public List<Item> items = new ArrayList<>();
	public List<ItemXing> ix = new ArrayList<>();
	// �ӵ�
	public List<PlayerBullet> bulletOfPlayer = new ArrayList<>();
	public List<PlayerBullet> bulletTribleOfPlayer = new ArrayList<>();
	public List<EnemyBullet> bulletOfEnemy = new ArrayList<>();

	// �ɻ�
	public PlayerPlane player01 = new PlayerPlane(this, Constant.GAME_WIDTH / 2, Constant.GAME_HEIGHT * 4 / 5,
			"Plane/Heroes/ying");
	public List<EnemyPlane> enemies = new ArrayList<>();
	// ��ը
	public List<Explode> explodes = new ArrayList<>();
	//��ʼ��������
	MusicThread m1=new MusicThread("src/com/neusoft/planewar/music/start_Bgm.mp3");

	@Override
	public void loadFrame() {
		// TODO Auto-generated method stub
		super.loadFrame();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub\
				player01.keyPressed(e);

			}

			public void keyReleased(KeyEvent e) {
				player01.keyReleased(e);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(e.getX()+" "+e.getY());
				if((e.getX()>=210&&e.getX()<=340)&&(e.getY()>=560&&e.getY()<=600)) {
					start=true;
					m1.player.close();
					
					new MusicThread("src/com/neusoft/planewar/music/v3.mp3").start();
				}
				if((e.getX()>=210&&e.getX()<=340)&&(e.getY()>=610&&e.getY()<=641)) {
					help=true;
				}
				
				
			}
			
		});
		if(enemies.size()==0) {
			Random r=new Random();
			int x=r.nextInt(5);
			for (int i = 0; i < 5; i++) {
				enemies.add(new EnemyPlane(this, 0 + i * 100, i*20, "Plane/Enemy/worker_"+x));
			}		
		}
//		// ���ص���
//		for (int i = 0; i < 5; i++) {
//			enemies.add(new EnemyPlane(this, 0 + i * 100, 50, "Plane/Enemy/worker_bee"));
//		}
		//new MusicThread("src/com/neusoft/planewar/music/v3.mp3").start();
		try {
			m1.player.play();
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * �������ķ���
	 * 
	 * @param g
	 */
	public void drawBG(Graphics g) {
		times++;
		if(player01.speedPowerTimer>0) {
			bgspeed=6;
		}else {
			bgspeed=3;
		}
		if (times % 3 == 0) {
			bgy += bgspeed;
		}
		if (times % bg.getHeight(null) == 0)
			times = 0;

		if (bgy >768)
			bgy = 0;
		
		g.drawImage(bg, 0, bgy, null);
		g.drawImage(bg, 0, -768 + bgy, null);


	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// background
		//��ʼ�������
		if(!player01.isLive()) {
			isPause=true;
		}
		g.drawImage(GameUtil.getImagePng("start/start3"), 0, 0, 512,768,null);
		g.drawImage(GameUtil.getImagePng("start/help_button"), 200,600,null);
		g.drawImage(GameUtil.getImagePng("start/start_menu"), 200, 550,null);
		if(help) {
			g.drawImage(GameUtil.getImagePng("start/help2"), 100,50,null);
			
		}
		if(enemies.size()==0) {
			Random r=new Random();
			int x=r.nextInt(5);
			for (int i = 0; i < 5; i++) {
				enemies.add(new EnemyPlane(this, 0 + i * 100, i*20, "Plane/Enemy/worker_"+x));
			}		
		}

//		if(enemies.size()==0) {
//			for (int i = 0; i < 5; i++) {
//				enemies.add(new EnemyPlane(this, 0 + i * 100, 0, "Plane/Enemy/worker_bee"));
//			}		
//		}
		if(start) {
			drawBG(g);
			//Ѫ��
			    g.drawImage(GameUtil.getImagePng("background/blood"), 380,30,null);
				g.setColor(Color.red);
				g.drawRect(410,40,100,10);
				int length=(int) (player01.getHealth());
				g.fillRect(410,40,length,10);
				
				// player1;	
				player01.draw(g);
				int [] scores=player01.getScore();
				for(int i=0;i<scores.length;i++) {
					g.drawImage(Images.get("digital"+scores[i]), 25+i*25, 30,21,28, null);
				}
//				int [] healths=player01.getHealths();
//				String s=((Integer)player01.getHealth()).toString();
//				for(int i=0;i<s.toString().length();i++) {
//					g.drawImage(Images.get("digital"+s.charAt(i)), 10+i*25, 60,21,28, null);
//				}
			// �ӵ�
			for (int i = 0; i < bulletOfPlayer.size(); i++) {
				PlayerBullet bullet = bulletOfPlayer.get(i);
				
				bullet.draw(g);
				bullet.hitPlane(enemies);
			}
			
			for (int i = 0; i < bulletOfEnemy.size(); i++) {
				EnemyBullet bullet = bulletOfEnemy.get(i);
				bullet.dir = Direction.DOWN;
				bullet.draw(g);
				bullet.hitPlane(player01);
			}
			//g.drawString("bulletOfEnemy"+bulletOfEnemy.size()+"", 0, 320);
			// item
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				item.draw(g);
				item.hitPlayer(player01);
			}
			//g.drawString("item"+items.size()+"", 0, 340);
			for (int i = 0; i < ix.size(); i++) {
				ItemXing xing = ix.get(i);
				xing.draw(g);
				xing.hitPlayer(player01);
			}
			//g.drawString("ix"+ix.size()+"", 0, 260);

			// ��ը
			for (int i = 0; i < explodes.size(); i++) {
				Explode e = explodes.get(i);
				e.draw(g);
			}
			//g.drawString("explodes"+explodes.size()+"", 0, 280);
			/// ����
			for (int i = 0; i < enemies.size(); i++) {
				EnemyPlane enemy = enemies.get(i);
				enemy.draw(g);
				enemy.isHit(player01);

			}
			//g.drawString("enemies"+enemies.size()+"", 0, 200);
			
		}
		
	}
	public void paint2(Graphics g) {
		// TODO Auto-generated method stub
		
		//������Ϸʧ��ͼƬ
		g.drawImage(GameUtil.getImagePng("background/gameover"), 0,0,null);
		//��������ܳɼ�
		int [] scores=player01.getScore();
		for(int i=0;i<scores.length;i++) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("΢���ź�",Font.BOLD,50));
			g.drawString("�ܳɼ���", 30, 280);
			g.drawImage(Images.get("digital"+scores[i]), 220+i*50, 230,40,48, null);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PlaneWarClient().loadFrame();
	}

}

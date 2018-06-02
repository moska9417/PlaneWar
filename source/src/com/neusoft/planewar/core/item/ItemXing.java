package com.neusoft.planewar.core.item;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.Item;
import com.neusoft.planewar.util.GameUtil;

public class ItemXing extends Item{
	int times=0;
	int Imgnum=1;
	int newWidth;
	int previousWidth;
	public boolean magnet=false;
	public ItemXing() {
		// TODO Auto-generated constructor stub
		img=GameUtil.getImagePng("item/xing/1");
	}
	public ItemXing(PlaneWarClient pwc, int x, int y) {
		// TODO Auto-generated constructor stub
		this();
		this.pwc=pwc;
		this.x=x;
		this.y=y;
		this.speed=-10;
	}
	public int getImgnum() {
		return Imgnum;
	}
	public void setImgnum(int imgnum) {
		Imgnum = imgnum;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub]
		if(magnet) {
			x+=(x<this.pwc.player01.x)?10:x==0?0:-10;
			y+=(y<this.pwc.player01.y)?10:y==0?0:-10;
			System.out.println("a");
		}else {
			changespeed();
			reverse();
			y+=speed;
		}
		if(y>Constant.GAME_HEIGHT) {
			pwc.ix.remove(this);
		}
	}
	private void reverse() {
		// TODO Auto-generated method stub
		if(times%2==0) {
			times=0;
			try {
				previousWidth=GameUtil.getImagePng("item/xing/"+getImgnum()).getWidth(null);
				setImgnum(getImgnum()+1);
				newWidth=GameUtil.getImagePng("item/xing/"+getImgnum()).getWidth(null);
				x=x+previousWidth/2-newWidth/2;
				img=GameUtil.getImagePng("item/xing/"+getImgnum());
				
			}catch (Exception e) {
				setImgnum(1);
				newWidth=GameUtil.getImagePng("item/xing/"+getImgnum()).getWidth(null);
				x=x+previousWidth/2-newWidth/2;
				img=GameUtil.getImagePng("item/xing/"+getImgnum());
			}
			
		}times++;
	}
}

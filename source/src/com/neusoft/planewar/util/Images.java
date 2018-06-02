package com.neusoft.planewar.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Images{
	private static Map<String,Image>imgs= new HashMap<>();
    //静态模块
    static {
        //我方飞机01号飞机的01号子弹
        
      imgs.put("plane10",GameUtil.getImagePng("Plane/Enemy/worker_bee"));
      imgs.put("bullet000f",GameUtil.getImagePng("Bullet/000f"));
      imgs.put("bullet009",GameUtil.getImagePng("Bullet/009"));
      imgs.put("bullet36",GameUtil.getImagePng("Bullet/36"));
      
      imgs.put("bullet000fE",GameUtil.getImagePng("Bullet/000fE"));
      for(int i=1;i<10;i++) {
    	  imgs.put("ying"+i,GameUtil.getImagePng("Plane/Heroes/ying/"+i));  
      }
      for(int i=1;i<=16;i++) {
    	  imgs.put("explode"+i,GameUtil.getImagePng("explode/"+i));
      }
      
      for(int i=0;i<10;i++) {    	  
    	  imgs.put("digital"+i, GameUtil.getImagePng("digital/"+i));
      }
      
      //item
      imgs.put("item1",GameUtil.getImagePng("item/arrow"));
      imgs.put("item2",GameUtil.getImagePng("item/beryl"));
      imgs.put("item3",GameUtil.getImagePng("item/Double_Star"));
      imgs.put("item4",GameUtil.getImagePng("item/DoubleExp"));
      imgs.put("item5",GameUtil.getImagePng("item/DoubleSpeed"));
      imgs.put("item6",GameUtil.getImagePng("item/ExtraLife"));
      imgs.put("item7",GameUtil.getImagePng("item/Lightning"));
      imgs.put("item8",GameUtil.getImagePng("item/Magnet"));
      imgs.put("item9",GameUtil.getImagePng("item/MaxPower"));
      imgs.put("item10",GameUtil.getImagePng("item/N-bomb"));
      imgs.put("item11",GameUtil.getImagePng("item/QuintuplePower"));
      imgs.put("item12",GameUtil.getImagePng("item/ruby"));
      imgs.put("item13",GameUtil.getImagePng("item/Sprint"));
      imgs.put("item14",GameUtil.getImagePng("item/Triple_Arrow"));
      imgs.put("bullet38",GameUtil.getImagePng("Bullet/38"));
      
    }
    public static Image get(String key){
        return imgs.get(key);
    }
}
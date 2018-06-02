package com.neusoft.planewar.core;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
public class MusicThread  extends Thread{
	
	public Player player;
	File music;
	public MusicThread(String musicPath) {
		this.music=new File(musicPath);
		BufferedInputStream buffer = null;
		try {
			buffer = new BufferedInputStream(new FileInputStream(music));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			player = new Player(buffer);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			play();
			
		}catch(FileNotFoundException |JavaLayerException e) {
			e.printStackTrace();
		}
	}
	public void play() throws FileNotFoundException,JavaLayerException{
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		player = new Player(buffer);
		player.play();
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MusicThread Test =new MusicThread("src/com/neusoft/planewar/music/v3.mp3");
		//Test.start();
	}

}

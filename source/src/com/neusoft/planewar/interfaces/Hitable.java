package com.neusoft.planewar.interfaces;

import java.util.List;

import com.neusoft.planewar.abstracts.PlaneWarObject;

/**
 * 飞机大战中的接口<br>
 * 如果让元素可以检测碰撞
 * @author Administrator
 * @version 1.0.1
 *
 */
public interface Hitable {
	boolean isHit( PlaneWarObject object);
}

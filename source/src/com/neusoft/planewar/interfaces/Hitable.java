package com.neusoft.planewar.interfaces;

import java.util.List;

import com.neusoft.planewar.abstracts.PlaneWarObject;

/**
 * �ɻ���ս�еĽӿ�<br>
 * �����Ԫ�ؿ��Լ����ײ
 * @author Administrator
 * @version 1.0.1
 *
 */
public interface Hitable {
	boolean isHit( PlaneWarObject object);
}

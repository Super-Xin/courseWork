package day03;

import java.awt.Color;

public class Ball {

	int x,y,width,speed,state; //��ʼ����ֱ�����ٶȣ�״̬��
	Color color;	//��ɫ
	public Ball()
	{
		x=(int)(Math.random()*BallFrame.WIDTH);
		y=(int)((Math.random()*100)+260);
		width=(int)((Math.random()*20)+50);
		speed=(int)((Math.random()*3)+1);
		state=(int)(Math.random()*4);
		int red=(int)(Math.random()*255);
		int green=(int)(Math.random()*255);
		int blue=(int)(Math.random()*255);
		color = new Color(red,green,blue);
	}
}

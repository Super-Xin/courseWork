package day03;

import java.awt.Color;

public class Brick {
	int width,height,x,y;
	Color color;
	public Brick()
	{	
		width = (int)((Math.random()*50)+150);
		height = 30;
		x = (int)(Math.random()*(BallFrame.WIDTH-width));
		y = (int)(Math.random()*250);
		int red=(int)(Math.random()*255);
		int green=(int)(Math.random()*255);
		int blue=(int)(Math.random()*255);
		color = new Color(red,green,blue);
	}
	public void height_minus()
	{
		if(height<0)
		{
			height=0;
		}
		if(height>0)
		{
			height-=10;
		}
	}
}

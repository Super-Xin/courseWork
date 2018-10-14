package day05;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss {
	static Image images[] = new Image[4];
	static{
		for(int i=0;i<images.length;i++)
		{
			images[i] = new ImageIcon("plane/boss"+i+".png").getImage();
		}
	}
	int x;
	int y;
	int state;
	int index = 0;
	int bNum = 4;
    int blood = 2000;
	int bossXState=2;
	int bossYState=1;
	Image bossImg;
	public Boss(int type)
	{
		state = type;
		x = (int)(Math.random()*200);
		if(state==1)
		y = 768;
		else
		y=-300;
		if(state==0)
		bossImg = images[0];
		else if(state==1)
		bossImg = images[2];
	}
	public void step()
	{
		if(state==0)
			bossImg = images[index++/10%2];
		else if(state==1)
			bossImg = images[index++/10%2+2];
		if(index==10000)
			index=0;
	}
	public boolean outOfBound()
	{
		if(y<-300||y>768)
			return true;
		else
			return false;
	}
	public void Move()
	{
		if(state==1)
		{
			y--;
		}
		if(state==0)
		{
			if(bossYState==0)
			{
				y--;
			}
			if(bossYState==1)
			{
				y++;
			}
			if(bossXState==2)
			{
				x--;
			}
			if(bossXState==3)
			{
				x++;
			}
			if(x<=0)
				bossXState=3;
			if(x>=200)
				bossXState=2;
			if(y>=300)
				bossYState=0;
			if(y<=0)
				bossYState=1;
		}
	}
	public Bullet[] shoot()
	{
		Bullet bullet[] = new Bullet[bNum];
		for(int i=0;i<bNum;i++)
		{
			bullet[i] = new Bullet(2,x+(this.bossImg.getWidth(null)/(bNum+1))*(i+1),y+this.bossImg.getHeight(null)+10);
		}
		return bullet;
	}
}

package day05;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Hero {
	//储存英雄机图片
	static Image[] images = new Image[10];
	//英雄机正在使用的静态图片
	Image HeroImg;
	//记录吃到小红心的数量
	static int NumRedHearts = 0;
	//火力
	String str[] = {"单倍","双倍","三倍","四倍","五倍","六倍","七倍","八倍"};
	int bulletNum = 1;
	int temp = 0;
	//图片资源静态载入
	static{
		images[0] = new ImageIcon("plane/ws00.png").getImage();
		images[1] = new ImageIcon("plane/ws01.png").getImage();
		images[2] = new ImageIcon("plane/ws02.png").getImage();
		images[3] = new ImageIcon("plane/ws03.png").getImage();
		images[4] = new ImageIcon("plane/ws04.png").getImage();
		images[5] = new ImageIcon("plane/ws05.png").getImage();
		images[6] = new ImageIcon("plane/ws06.png").getImage();
		images[7] = new ImageIcon("plane/ws07.png").getImage();
		images[8] = new ImageIcon("plane/ws08.png").getImage();
		images[9] = new ImageIcon("plane/ws09.png").getImage();
	}
	//英雄机坐标
	int Hx,Hy,index;
	//英雄机血量
	int blood=100;
	public Hero()
	{
		Hx = (int)(Math.random()*467);
		Hy = 550;
		index = 0;
		
	}
	public void step()
	{
			HeroImg = images[index++/10%images.length];
			if(index==10000)
			index=0;
	}
	public Bullet[] shoot()
	{
		Bullet bullet[] = new Bullet[bulletNum];
		for(int i=0;i<bulletNum;i++)
		{
			bullet[i] = new Bullet(0,Hx+(this.HeroImg.getWidth(null)/(bulletNum+1))*(i+1),Hy-10);
		}
		return bullet;
	}
	public void RedHeartsbulletNum()
	{
		if((NumRedHearts)%5==0&&bulletNum<7&&temp!=NumRedHearts)
		{
			bulletNum++;
			temp=NumRedHearts;
		}
		if(index%1000==0&&bulletNum>1)
		bulletNum--;
		if(NumRedHearts>=50)
		{
			bulletNum=8;
			NumRedHearts=0;
			temp=0;
		}
	}
	public int getRedHeartsNum()
	{
		return NumRedHearts;
	}
	public void upRedHearts()
	{
		NumRedHearts++;
	}
	public void downRedHearts(int i)
	{
		NumRedHearts-=i;
	}
}

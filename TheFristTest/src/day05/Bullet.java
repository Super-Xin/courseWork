package day05;

import java.awt.Image;
import javax.swing.ImageIcon;

//子弹类
public class Bullet {
	//子弹类型
	int type;
	//子弹的位置
	int x,y;
	//子弹图片
	Image bulletImg;
	//静态载入图片
	static Image images[] = new Image[4];
	static{
			images[0] = new ImageIcon("plane/bullets.png").getImage();
			images[1] = new ImageIcon("plane/bossbu0.png").getImage();
			images[2] = new ImageIcon("plane/bossbu1.png").getImage();
			images[3] = new ImageIcon("plane/bossbu1.png").getImage();
	}
	public Bullet(int i,int x,int y)
	{
		type = i;
		this.x=x;
		this.y=y;
		//英雄机子弹
		if(type==0)
		bulletImg = images[0];
		//boss1
		if(type==1)
		bulletImg = images[1];
		//boss2
		if(type==2)
		bulletImg = images[2];
		//boss3
		if(type==3)
		bulletImg = images[3];
	}
	public void step()
	{
		if(type==0)
		y-=15;
		if(type==1)
		y+=5;
		if(type==2)
		y+=8;
	}
	//检查越界
	public boolean outOfBounds()
	{
		if(y<0||y>768)
			return true;
		else
			return false;
	}
	//检查英雄机子弹碰撞敌机
	public boolean impact(Enemy e)
	{
			if(x>=e.Ex&&x<=e.Ex+e.EnemyImg.getWidth(null)&&y<=e.Ey+e.EnemyImg.getHeight(null)&&y>=e.Ey)
				return true;
			else
				return false;
	}
	//检查敌机子弹碰撞英雄机
	public boolean impact(Hero h)
	{
			if(x>=h.Hx&&x<=h.Hx+h.HeroImg.getWidth(null)&&y>=h.Hy&&y<=h.Hy+h.HeroImg.getHeight(null))
				return true;
			else
				return false;
	}
	//检查英雄机子弹碰撞boss机
	public boolean impact(Boss b)
	{
			if(x>=b.x&&x<=b.x+b.bossImg.getWidth(null)&&y<=b.y+b.bossImg.getHeight(null)&&y>=b.y)
				return true;
			else
				return false;
	}
}

package day05;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

//敌机类
public class Enemy {
	Image EnemyImg;
	static Image images[] = new Image[7];
	{
		images[0] = new ImageIcon("plane/flys.png").getImage();
		images[1] = new ImageIcon("plane/flys0.png").getImage();
		images[2] = new ImageIcon("plane/flys1.png").getImage();
		images[3] = new ImageIcon("plane/flys2.png").getImage();
		images[4] = new ImageIcon("plane/flys3.png").getImage();
		images[5] = new ImageIcon("plane/flys4.png").getImage();
		images[6] = new ImageIcon("plane/flys5.png").getImage();
	}
	int Ex,Ey;
	int speed=1;
	int index=0;
	int xstate=0;
	int ystate=0;
	int blood=100;
	public Enemy(int x,int y){
		this.Ex=x;
		this.Ey=y;
	}
	public Bullet Enemyshoot()
	{
		Bullet bullet = new Bullet(1,Ex+30,Ey+50);
		return bullet;
	}
	public void step()
	{
		EnemyImg = images[index++/10%images.length];
		if(index==10000)
		index=0;
	}
	//判断敌机碰壁
	public void speed()
	{
		Random rb =new Random();
		if(xstate==0)
			Ex++;
		if(xstate==1)
			Ex--;
		if(ystate==0)
			Ey++;
		if(ystate==1)
			Ey--;
		if(Ex>=450)
			xstate=1;
		if(Ex<=0)
			xstate=0;
		if(rb.nextInt(100)==1)
		if(Ey>=300&&Ey<=350)
			ystate=1;
		if(Ey<=0)
			ystate=0;
	}
	//检查越界
	public boolean outOfBounds()
	{
		if(Ey>768)
			return true;
		else
			return false;
	}
}

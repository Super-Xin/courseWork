package day05;

import java.awt.Image;
import javax.swing.ImageIcon;

public class RedHearts {
	//���ĵ�λ��
	int Rx,Ry;
	//С����״̬
	int state = (int)(Math.random()*2);
	//�ƶ��ٶ�
	int speed=5;
	//���ĵ�ͼƬ��Դ����Դ���滻
	Image RHImg;
	//ͼƬ��̬����
	static Image images[] = new Image[9];
	static{
		images[0] = new ImageIcon("plane/qq00.png").getImage();
		images[1] = new ImageIcon("plane/qq01.png").getImage();
		images[2] = new ImageIcon("plane/qq02.png").getImage();
		images[3] = new ImageIcon("plane/qq03.png").getImage();
		images[4] = new ImageIcon("plane/qq04.png").getImage();
		images[5] = new ImageIcon("plane/qq05.png").getImage();
		images[6] = new ImageIcon("plane/qq06.png").getImage();
		images[7] = new ImageIcon("plane/qq07.png").getImage();
		images[8] = new ImageIcon("plane/qq08.png").getImage();
	}
	int index;
	//���캯��
	public RedHearts(int x , int y)
	{
		Rx = x;
		Ry = y;
		index = 0;
	}
	public void step()
	{
		RHImg = images[index++/10%images.length];
	}
	//�ж����ڲ��ƶ�
	public void speed()
	{
		if(Rx>=450)
			state=1;
		if(Rx<=0)
			state=0;
		if(state==0)
		{
			Rx+=speed;
			Ry+=speed-2;
		}
		if(state==1)
		{
			Rx-=speed;
			Ry+=speed;
		}
	}
	
	//���Խ��
	public boolean outOfBounds()
	{
		if(Ry>768)
			return true;
		else
			return false;
	}
	//�жϳԵ�С����
	public boolean eatHearts(Hero h)
	{
		if(h.Hx+h.HeroImg.getWidth(null)/2>=Rx&&
				h.Hx+h.HeroImg.getWidth(null)/2<=Rx+this.RHImg.getWidth(null)&&
				h.Hy+h.HeroImg.getHeight(null)/2>=Ry&&
				h.Hy+h.HeroImg.getHeight(null)/2<=Ry+this.RHImg.getHeight(null))
		return true;
		else
		return false;
	}
}

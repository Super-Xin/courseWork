package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class StarPanel extends JPanel {
	int x[] = new int[150];
	int y[] = new int[150];
	int r,g1,b;
	
	//面板类
	//构造
	//paint方法
	public StarPanel(){
		for(int i=0;i<150;i++){
			x[i] = (int)(Math.random()*1920);
			y[i] = (int)(Math.random()*1080);
		}
		//设置背景颜色
		setBackground(Color.BLACK);
	}
	//设置画笔
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font("宋体",Font.BOLD,20));
		for(int i=0;i<150;i++){
		r=(int)(Math.random()*255);
		g1=(int)(Math.random()*255);
		b=(int)(Math.random()*255);
		g.setColor(new Color(r,g1,b));
		g.drawString("*", x[i], y[i]);
	}
		//设置文字
		/*
		g.setFont(new Font("华文行楷",Font.BOLD,100));
		g.setColor(Color.YELLOW);
		g.drawString("我喜欢你Baby Cui",550, 1080/2);
		*/
		//设置月亮
		g.setColor(Color.YELLOW);
		g.fillOval(100, 100, 100, 100);
		g.setColor(Color.BLACK);
		g.fillOval(140, 130, 100, 100);
		/*//设置大黄脸
		//黄脸
		g.setColor(new Color(254,189,91));
		g.fillOval(500,290, 200, 200);
		//左眉毛
		g.setColor(Color.BLACK);
		g.fillOval(540, 320, 70, 70);
		g.setColor(new Color(254,189,91));
		g.fillOval(534, 325, 100, 100);
		//右眉毛
		g.setColor(Color.BLACK);
		g.fillOval(593, 330, 64, 70);
		g.setColor(new Color(254,189,91));
		g.fillOval(564, 332, 98, 100);
		//左眼睛
		g.setColor(Color.BLACK);
		g.fillOval(540, 350, 60, 60);
		g.setColor(Color.WHITE);
		g.fillOval(543, 353, 54, 54);
		g.setColor(Color.BLACK);
		g.fillOval(570, 370, 20, 20);
		//右眼睛
		g.setColor(Color.BLACK);
		g.fillOval(613, 360, 46,46);
		g.setColor(Color.WHITE);
		g.fillOval(616, 363, 40,40);
		g.setColor(Color.BLACK);
		g.fillOval(620, 375, 15,15);
		//嘴
		g.setColor(Color.BLACK);
		g.fillOval(562, 430, 80, 53);
		g.setColor(new Color(254,189,91));
		g.fillOval(556, 433, 90, 56);
		//文字
		g.setFont(new Font("华文行楷",Font.BOLD,50));
		g.setColor(new Color(224,189,91));
		g.drawString("妈妈说我好丑啊...", 430, 600);*/
		}
	public void action(){
		//设置定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run()
			{
				for(int i=0;i<150;i++)
				{
					y[i]++;
					x[i]--;
					repaint();
					if(y[i]>1080)
					y[i]=0;
					if(x[i]<0)
					x[i]=1920;
				}
			}
		},0,5); 
	}
}

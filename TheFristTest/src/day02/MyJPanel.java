package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

//面板
public class MyJPanel extends JPanel{
	//构造基本信息
	public MyJPanel(){
		//设置背景颜色
		setBackground(Color.WHITE);
		
	}
	//设置paint画笔
	public void paint(Graphics g)
	{
		super.paint(g);
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
		//设置文字
		g.setFont(new Font("华文行楷",Font.BOLD,50));
		g.setColor(new Color(224,189,91));
		g.drawString("妈妈说我好丑啊...", 430, 600);
	}
}

package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

//���
public class MyJPanel extends JPanel{
	//���������Ϣ
	public MyJPanel(){
		//���ñ�����ɫ
		setBackground(Color.WHITE);
		
	}
	//����paint����
	public void paint(Graphics g)
	{
		super.paint(g);
		//����
		g.setColor(new Color(254,189,91));
		g.fillOval(500,290, 200, 200);
		//��üë
		g.setColor(Color.BLACK);
		g.fillOval(540, 320, 70, 70);
		g.setColor(new Color(254,189,91));
		g.fillOval(534, 325, 100, 100);
		//��üë
		g.setColor(Color.BLACK);
		g.fillOval(593, 330, 64, 70);
		g.setColor(new Color(254,189,91));
		g.fillOval(564, 332, 98, 100);
		//���۾�
		g.setColor(Color.BLACK);
		g.fillOval(540, 350, 60, 60);
		g.setColor(Color.WHITE);
		g.fillOval(543, 353, 54, 54);
		g.setColor(Color.BLACK);
		g.fillOval(570, 370, 20, 20);
		//���۾�
		g.setColor(Color.BLACK);
		g.fillOval(613, 360, 46,46);
		g.setColor(Color.WHITE);
		g.fillOval(616, 363, 40,40);
		g.setColor(Color.BLACK);
		g.fillOval(620, 375, 15,15);
		//��
		g.setColor(Color.BLACK);
		g.fillOval(562, 430, 80, 53);
		g.setColor(new Color(254,189,91));
		g.fillOval(556, 433, 90, 56);
		//��������
		g.setFont(new Font("�����п�",Font.BOLD,50));
		g.setColor(new Color(224,189,91));
		g.drawString("����˵�Һó�...", 430, 600);
	}
}

package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class snakeJpane extends JPanel implements KeyListener{
	//ͼƬ��Դ����
	 Image title = new ImageIcon("snakeimg/title.jpg").getImage();
	//����һ����
	 snakeInfo snake = new snakeInfo();
	//����һ��ʳ��
	 Random a = new Random();
	 snakeFood food = new snakeFood(a.nextInt(34)*25+25,a.nextInt(24)*25+75);
	//��Ϸ�Ƿ�ʼ
	 boolean isStart = true;
	//��Ϸ״̬
	 boolean gameState = true;
	//��Ϸ�÷�
	 int score;
	//��Ϸ��Ч
	 music musics = new music();
	//���ùؿ�
	 String name[] = {"��һ��","�ڶ���","������"};
	 int time = 100;
	 int count = 0;
	//���캯��
	public snakeJpane()
	{
		setBackground(Color.WHITE);
		setVisible(false);
		setFocusable(true);
		musics.startBgMusic();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		//������
		g.drawImage(title,25,11,null);
		//����Ϸ��
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		//���÷֣�������
		g.setColor(Color.YELLOW);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("�����ֵ�",100, 32);
		g.drawString("�������ϳ���",80, 57);
		g.drawString("�÷֣�"+(score+200*count),700, 32);
		g.drawString("�ؿ�:"+name[count],700, 57);
		//��������
		paintSanke(g);
		//��ʳ��
		paintEatFood(g);
		//�ж���Ϸ״̬
		if(!isStart)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("����",Font.BOLD,30));
			if(gameState)
			g.drawString("���ո����ʼ/��ͣ��Ϸ", 280, 350);
			else
			{
				g.drawString("��Ϸ���������ո������", 280, 350);
				musics.stoopBgMusic();
				musics.startShiBaiMusic();
			}
		}
	}
	public void action()
	{
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run()
			{
				if(isStart)
				{
					//�ƶ�������
					moveSanke();
					//��ʳ��
					isEatFood();
					//�ж��Ƿ�����
					if(snake.isDeath())
					{
						gameState = false;
						isStart = false;
						score+=count*200;
						time=100;
						count=0;
						snake = new snakeInfo();
					}
					//�����ؿ�
					if(score!=0&&count<2&&gameState==true)
					if(score%200==0)
					{
						score=0;
						time-=30;
						count++;
						timer.cancel();
						action();
					}
					//��ʱ����λ
					if(gameState==false)
					{
						timer.cancel();
						action();
					}
					//�ػ�
					repaint();
				}
			}
		},100,time);
	}
	public void paintSanke(Graphics g)
	{
		//����ͷ
		g.drawImage(snake.snakeImg[snake.state], snake.x[0], snake.y[0], null);
		//
		//��������
		for(int i=1;i<snake.len;i++)
		{
			g.drawImage(snake.snakeImg[0], snake.x[i], snake.y[i], null);
		}
	}
	public void moveSanke()
	{
		//�ƶ�������
		for(int i=(snake.len-1);i>0;i--)
		{
			snake.x[i] = snake.x[i-1];
			snake.y[i] = snake.y[i-1];
		}
		//�ƶ���ͷ
		//��
		if(snake.state==1)
		{
			snake.y[0]-=25;
		}
		//��
		if(snake.state==2)
		{
			snake.y[0]+=25;
		}
		//��
		if(snake.state==3)
		{
			snake.x[0]-=25;
		}
		//��
		if(snake.state==4)
		{
			snake.x[0]+=25;
		}
	}
	//��ʳ��
	public void paintEatFood(Graphics g)
	{
		g.drawImage(food.foodImg,food.x,food.y,null);
	}
	//�жϳԵ�ʳ��
	public void isEatFood()
	{
		if(snake.isEatFood(food))
		{
			food.x = a.nextInt(34)*25+25;
			food.y = a.nextInt(24)*25+75;
			snake.x[snake.len]=snake.x[snake.len-1];
			snake.y[snake.len]=snake.y[snake.len-1];
			snake.len++;
			score+=10;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//���̰�������
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode ==KeyEvent.VK_SPACE)
		{
			isStart=!isStart;
			repaint();
			//��Ϸ��������Ȼ��ʾ����,�Լ��ؿ����ø�λ
			if(gameState==false)
			{
				score=0;
				gameState=true;
				musics.startBgMusic();
			}
		}
		if(isStart){
		//��
		if(keyCode==KeyEvent.VK_UP&&snake.state!=2)
		{
			snake.state=1;
		}
		//��
		if(keyCode==KeyEvent.VK_DOWN&&snake.state!=1)
		{
			snake.state=2;
		}
		//��
		if(keyCode==KeyEvent.VK_LEFT&&snake.state!=4)
		{
			snake.state=3;
		}
		//��
		if(keyCode==KeyEvent.VK_RIGHT&&snake.state!=3)
		{
			snake.state=4;
		}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}

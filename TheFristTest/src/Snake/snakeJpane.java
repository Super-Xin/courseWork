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
	//图片资源载入
	 Image title = new ImageIcon("snakeimg/title.jpg").getImage();
	//创建一条蛇
	 snakeInfo snake = new snakeInfo();
	//创建一个食物
	 Random a = new Random();
	 snakeFood food = new snakeFood(a.nextInt(34)*25+25,a.nextInt(24)*25+75);
	//游戏是否开始
	 boolean isStart = true;
	//游戏状态
	 boolean gameState = true;
	//游戏得分
	 int score;
	//游戏音效
	 music musics = new music();
	//设置关卡
	 String name[] = {"第一关","第二关","第三关"};
	 int time = 100;
	 int count = 0;
	//构造函数
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
		//画标题
		g.drawImage(title,25,11,null);
		//画游戏框
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		//画得分，及标语
		g.setColor(Color.YELLOW);
		g.setFont(new Font("黑体",Font.BOLD,20));
		g.drawString("塑料兄弟",100, 32);
		g.drawString("不做塑料程序",80, 57);
		g.drawString("得分："+(score+200*count),700, 32);
		g.drawString("关卡:"+name[count],700, 57);
		//画蛇身体
		paintSanke(g);
		//画食物
		paintEatFood(g);
		//判断游戏状态
		if(!isStart)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("黑体",Font.BOLD,30));
			if(gameState)
			g.drawString("按空格键开始/暂停游戏", 280, 350);
			else
			{
				g.drawString("游戏结束，按空格键继续", 280, 350);
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
					//移动蛇身体
					moveSanke();
					//吃食物
					isEatFood();
					//判断是否死亡
					if(snake.isDeath())
					{
						gameState = false;
						isStart = false;
						score+=count*200;
						time=100;
						count=0;
						snake = new snakeInfo();
					}
					//调整关卡
					if(score!=0&&count<2&&gameState==true)
					if(score%200==0)
					{
						score=0;
						time-=30;
						count++;
						timer.cancel();
						action();
					}
					//定时器复位
					if(gameState==false)
					{
						timer.cancel();
						action();
					}
					//重汇
					repaint();
				}
			}
		},100,time);
	}
	public void paintSanke(Graphics g)
	{
		//画蛇头
		g.drawImage(snake.snakeImg[snake.state], snake.x[0], snake.y[0], null);
		//
		//画蛇身体
		for(int i=1;i<snake.len;i++)
		{
			g.drawImage(snake.snakeImg[0], snake.x[i], snake.y[i], null);
		}
	}
	public void moveSanke()
	{
		//移动蛇身体
		for(int i=(snake.len-1);i>0;i--)
		{
			snake.x[i] = snake.x[i-1];
			snake.y[i] = snake.y[i-1];
		}
		//移动蛇头
		//上
		if(snake.state==1)
		{
			snake.y[0]-=25;
		}
		//下
		if(snake.state==2)
		{
			snake.y[0]+=25;
		}
		//左
		if(snake.state==3)
		{
			snake.x[0]-=25;
		}
		//右
		if(snake.state==4)
		{
			snake.x[0]+=25;
		}
	}
	//画食物
	public void paintEatFood(Graphics g)
	{
		g.drawImage(food.foodImg,food.x,food.y,null);
	}
	//判断吃到食物
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
	//键盘按键接收
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode ==KeyEvent.VK_SPACE)
		{
			isStart=!isStart;
			repaint();
			//游戏结束后任然显示分数,以及关卡设置复位
			if(gameState==false)
			{
				score=0;
				gameState=true;
				musics.startBgMusic();
			}
		}
		if(isStart){
		//上
		if(keyCode==KeyEvent.VK_UP&&snake.state!=2)
		{
			snake.state=1;
		}
		//下
		if(keyCode==KeyEvent.VK_DOWN&&snake.state!=1)
		{
			snake.state=2;
		}
		//左
		if(keyCode==KeyEvent.VK_LEFT&&snake.state!=4)
		{
			snake.state=3;
		}
		//右
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

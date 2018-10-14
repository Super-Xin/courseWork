package day05;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class PlanePanel extends JPanel implements MouseMotionListener{

	//背景的图片属性和坐标属性
	Image bkImg;
	//计数器
	int index=0;
	int bx=0,by=0;
	//英雄机
	Hero hero = new Hero();
	//火力，游戏状态，关卡
	String str,gameState="";
	int score;
	//子弹集合
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	//小红心集合
	ArrayList<RedHearts> redHearts = new ArrayList<RedHearts>();
	//敌机集合
	ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	//boss机
	Boss bosses[] = new Boss[2];
	//构造
	public PlanePanel()
	{
		//鼠标监听
		addMouseMotionListener(this);
		//英雄机背景图载入
		bkImg = new ImageIcon("plane/background.jpg").getImage();
	}
	//paint 方法
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(bkImg,bx,by,null);
		g.drawImage(bkImg,bx,by-768,null);
		//画boss
		paintBoss(g);
		//画英雄机
		g.drawImage(hero.HeroImg,hero.Hx,hero.Hy,null);
		g.setFont(new Font("黑体",Font.BOLD,25));
		g.setColor(Color.YELLOW);
		str = hero.str[hero.bulletNum-1];
		g.drawString("火力:"+str,0, 720);
		g.drawString("小红心数量:"+hero.getRedHeartsNum(),170, 720);
		g.drawString("血量:"+hero.blood,380, 720);
		g.drawString("关卡:"+(score/1000+1),400,30);
		g.drawString("得分:"+score,10,30);
		//画英雄机子弹
		paintBullet(g);
		//画小红心
		paintRedHearts(g);
		//画敌机
		paintEnemy(g);
		//游戏状态
		g.setFont(new Font("黑体",Font.BOLD,100));
		g.setColor(Color.GREEN);
		g.drawString(gameState, 35, 370);
	}
	public void action(){
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run()
			{
				if((index++)==Integer.MAX_VALUE)
					index=0;
				//实现背景图片的运动
				by++;
				if(by>768)
				by=0;
				//实现英雄机的动态变化
				hero.step();
				//产生英雄机子弹，移动子弹
				if(index%10==0)
				actionBullet();
				//英雄机子弹变化
				hero.RedHeartsbulletNum();
				//产生敌机子弹
				if(index%50==0)
				EnemyActionBullet();
				moveBullet();
				//产生小红心，移动小红心，动态变化
				Random ra =new Random();
				if(ra.nextInt(100)==50)
				actionRedhearts();
				changeRedhearts();
				moveRedhearts();
				//产生敌机，移动敌机，动态变化
				Random rb =new Random();
				if(rb.nextInt(200)==100)
				actionEnemy();
				moveEnemy();
				changeEnemy();
				//产生boss，移动boss
				actionBoss();
				moveBoss();
				//判断子弹碰撞，并销毁对象
				impact();
				//判断对象越界，并删除
				outOfBounds();
				//判断是否吃到小红心
				isEatHearts();
				//判断游戏状态
				if(hero.blood<=0)
				{
					timer.cancel();
					gameState="GameOver";
				}
				//重汇
				repaint();
			}
		},100,10);
	}
	//画子弹方法
	public void paintBullet(Graphics g)
	{
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			g.drawImage(b.bulletImg, b.x, b.y, null);
		}
	}
	//产生英雄机子弹,放入集合
	public void actionBullet()
	{
		Bullet b[] = hero.shoot();
		for(int i=0;i<hero.bulletNum;i++)
		bullets.add(b[i]);
	}
	//移动子弹
	public void moveBullet()
	{
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			b.step();
		}
	}
	//产生小红心
	public void actionRedhearts()
	{
		RedHearts rh = new RedHearts((int)(Math.random()*450),-10);
		redHearts.add(rh);
	}
	//移动小红心
	public void moveRedhearts()
	{
		for(int i=0;i<redHearts.size();i++)
		{
			redHearts.get(i).speed();
		}
	}
	//画小红心
	public void paintRedHearts(Graphics g)
	{
		for(int i=0;i<redHearts.size();i++)
		{
			RedHearts rh = redHearts.get(i);
			g.drawImage(rh.RHImg,rh.Rx,rh.Ry,null);
		}
	}
	//小红心的动态变化
	public void changeRedhearts()
	{
		for(int i=0;i<redHearts.size();i++)
		{
			redHearts.get(i).step();
		}
	}
	//产生敌人机
	public void actionEnemy()
	{
		Enemy eny = new Enemy((int)(Math.random()*450),-20);
		enemys.add(eny);
	}
	//产生敌机子弹,放入集合
	public void EnemyActionBullet()
	{
		for(int i=0;i<enemys.size();i++)
		{
		Bullet b = enemys.get(i).Enemyshoot();
		bullets.add(b);
		}
	}
	//移动敌机
	public void moveEnemy()
	{
		for(int i=0;i<enemys.size();i++)
		{
			enemys.get(i).speed();
		}
	}
	//画敌机
	public void paintEnemy(Graphics g)
	{
		for(int i=0;i<enemys.size();i++)
		{
			Enemy eny = enemys.get(i);
			g.drawImage(eny.EnemyImg,eny.Ex,eny.Ey,null);
		}
	}
	//敌机的动态变化
	public void changeEnemy()
	{
		for(int i=0;i<enemys.size();i++)
		{
			enemys.get(i).step();
		}
	}
	//对象越界销毁
	public void outOfBounds()
	{
		//销毁越界的对象
		//销毁越界子弹
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			if(b.outOfBounds())
			bullets.remove(i);
		}
		//销毁越界敌机
		for(int i=0;i<enemys.size();i++)
		{
			Enemy e = enemys.get(i);
			if(e.outOfBounds())
			enemys.remove(i);
		}
		//销毁越界小红心
		for(int i=0;i<redHearts.size();i++)
		{
			RedHearts r = redHearts.get(i);
			if(r.outOfBounds())
			redHearts.remove(i);
		}	
		//销毁越界Boss机
		if(bosses[1]!=null)
		if(bosses[1].blood<=0)
		{
			bosses[0]=null;bosses[1]=null;
		}
	}
	//检查子弹碰撞
	public void impact()
	{
		for(int i=0;i<bullets.size();i++)
		{
			//检查英雄机子弹碰撞敌机
			for(int j=0;j<enemys.size();j++)
			{
				if(bullets.get(i).impact(enemys.get(j))&&bullets.get(i).type==0)
				{
					enemys.get(j).blood-=20;
					if(enemys.get(j).blood<=0)
					{
						enemys.remove(j);
						if(hero.blood<100)
						hero.blood++;
					}
					score+=10;
					bullets.remove(i);
					break;
				}
			}
		}
		for(int i=0;i<bullets.size();i++){
		//检查敌机子弹碰撞英雄机
			if(bullets.get(i).impact(hero)&&bullets.get(i).type==1)
			{
				bullets.remove(i);
				hero.blood-=5;
			}
		}
		for(int i=0;i<bullets.size();i++){
			//检查boss机子弹碰撞英雄机
				if(bullets.get(i).impact(hero)&&bullets.get(i).type==2)
				{
					bullets.remove(i);
					hero.blood-=5;
				}
			}
		if(bosses[1]!=null)
		for(int i=0;i<bullets.size();i++){
			//检查英雄机机子弹碰撞boss机，并销毁
				if(bullets.get(i).impact(bosses[1])&&bullets.get(i).type==0)
				{
					bullets.remove(i);
					bosses[1].blood-=5;
				}
			}
	}
	//检查是否吃吃到小红心
	public void isEatHearts()
	{
		for(int i=0;i<redHearts.size();i++)
		{
			if(redHearts.get(i).eatHearts(hero))
			{
				redHearts.remove(i);
				hero.upRedHearts();
			}	
		}
	}
	//产生Boss
	public void actionBoss()
	{
		if(score!=0&&score%1000==0&&bosses[0]==null)
			bosses[0] = new Boss(1);
		if(bosses[0]!=null&&bosses[0].outOfBound()&&bosses[1]==null)
		{
			bosses[1] = new Boss(0);
		}
	}
	//移动boss机
	public void moveBoss()
	{
		if(bosses[0]!=null)
			bosses[0].Move();
		if(bosses[1]!=null)
		{
			bosses[1].Move();
			if(index%50==0)
			for(int i=0;i<bosses[1].shoot().length;i++)
			{
				bullets.add(bosses[1].shoot()[i]);
			}
		}
	}
	//画boss机
	public void paintBoss(Graphics g)
	{
		if(bosses[0]!=null)
		{	
			bosses[0].step();
			g.drawImage(bosses[0].bossImg,bosses[0].x,bosses[0].y,null);
		}
		if(bosses[1]!=null)
		{
			bosses[1].step();
			g.drawImage(bosses[1].bossImg,bosses[1].x,bosses[1].y,null);
			g.setFont(new Font("黑体",Font.BOLD,25));
			g.setColor(Color.YELLOW);
			g.drawString("Boss血量:"+bosses[1].blood,170,30);
		}
			
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//鼠标控制英雄机
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		hero.Hx = e.getX()-hero.HeroImg.getWidth(null)/2;
		hero.Hy = e.getY()-hero.HeroImg.getHeight(null)/2;	
	}
}

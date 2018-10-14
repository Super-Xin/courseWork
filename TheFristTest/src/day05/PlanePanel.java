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

	//������ͼƬ���Ժ���������
	Image bkImg;
	//������
	int index=0;
	int bx=0,by=0;
	//Ӣ�ۻ�
	Hero hero = new Hero();
	//��������Ϸ״̬���ؿ�
	String str,gameState="";
	int score;
	//�ӵ�����
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	//С���ļ���
	ArrayList<RedHearts> redHearts = new ArrayList<RedHearts>();
	//�л�����
	ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	//boss��
	Boss bosses[] = new Boss[2];
	//����
	public PlanePanel()
	{
		//������
		addMouseMotionListener(this);
		//Ӣ�ۻ�����ͼ����
		bkImg = new ImageIcon("plane/background.jpg").getImage();
	}
	//paint ����
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(bkImg,bx,by,null);
		g.drawImage(bkImg,bx,by-768,null);
		//��boss
		paintBoss(g);
		//��Ӣ�ۻ�
		g.drawImage(hero.HeroImg,hero.Hx,hero.Hy,null);
		g.setFont(new Font("����",Font.BOLD,25));
		g.setColor(Color.YELLOW);
		str = hero.str[hero.bulletNum-1];
		g.drawString("����:"+str,0, 720);
		g.drawString("С��������:"+hero.getRedHeartsNum(),170, 720);
		g.drawString("Ѫ��:"+hero.blood,380, 720);
		g.drawString("�ؿ�:"+(score/1000+1),400,30);
		g.drawString("�÷�:"+score,10,30);
		//��Ӣ�ۻ��ӵ�
		paintBullet(g);
		//��С����
		paintRedHearts(g);
		//���л�
		paintEnemy(g);
		//��Ϸ״̬
		g.setFont(new Font("����",Font.BOLD,100));
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
				//ʵ�ֱ���ͼƬ���˶�
				by++;
				if(by>768)
				by=0;
				//ʵ��Ӣ�ۻ��Ķ�̬�仯
				hero.step();
				//����Ӣ�ۻ��ӵ����ƶ��ӵ�
				if(index%10==0)
				actionBullet();
				//Ӣ�ۻ��ӵ��仯
				hero.RedHeartsbulletNum();
				//�����л��ӵ�
				if(index%50==0)
				EnemyActionBullet();
				moveBullet();
				//����С���ģ��ƶ�С���ģ���̬�仯
				Random ra =new Random();
				if(ra.nextInt(100)==50)
				actionRedhearts();
				changeRedhearts();
				moveRedhearts();
				//�����л����ƶ��л�����̬�仯
				Random rb =new Random();
				if(rb.nextInt(200)==100)
				actionEnemy();
				moveEnemy();
				changeEnemy();
				//����boss���ƶ�boss
				actionBoss();
				moveBoss();
				//�ж��ӵ���ײ�������ٶ���
				impact();
				//�ж϶���Խ�磬��ɾ��
				outOfBounds();
				//�ж��Ƿ�Ե�С����
				isEatHearts();
				//�ж���Ϸ״̬
				if(hero.blood<=0)
				{
					timer.cancel();
					gameState="GameOver";
				}
				//�ػ�
				repaint();
			}
		},100,10);
	}
	//���ӵ�����
	public void paintBullet(Graphics g)
	{
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			g.drawImage(b.bulletImg, b.x, b.y, null);
		}
	}
	//����Ӣ�ۻ��ӵ�,���뼯��
	public void actionBullet()
	{
		Bullet b[] = hero.shoot();
		for(int i=0;i<hero.bulletNum;i++)
		bullets.add(b[i]);
	}
	//�ƶ��ӵ�
	public void moveBullet()
	{
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			b.step();
		}
	}
	//����С����
	public void actionRedhearts()
	{
		RedHearts rh = new RedHearts((int)(Math.random()*450),-10);
		redHearts.add(rh);
	}
	//�ƶ�С����
	public void moveRedhearts()
	{
		for(int i=0;i<redHearts.size();i++)
		{
			redHearts.get(i).speed();
		}
	}
	//��С����
	public void paintRedHearts(Graphics g)
	{
		for(int i=0;i<redHearts.size();i++)
		{
			RedHearts rh = redHearts.get(i);
			g.drawImage(rh.RHImg,rh.Rx,rh.Ry,null);
		}
	}
	//С���ĵĶ�̬�仯
	public void changeRedhearts()
	{
		for(int i=0;i<redHearts.size();i++)
		{
			redHearts.get(i).step();
		}
	}
	//�������˻�
	public void actionEnemy()
	{
		Enemy eny = new Enemy((int)(Math.random()*450),-20);
		enemys.add(eny);
	}
	//�����л��ӵ�,���뼯��
	public void EnemyActionBullet()
	{
		for(int i=0;i<enemys.size();i++)
		{
		Bullet b = enemys.get(i).Enemyshoot();
		bullets.add(b);
		}
	}
	//�ƶ��л�
	public void moveEnemy()
	{
		for(int i=0;i<enemys.size();i++)
		{
			enemys.get(i).speed();
		}
	}
	//���л�
	public void paintEnemy(Graphics g)
	{
		for(int i=0;i<enemys.size();i++)
		{
			Enemy eny = enemys.get(i);
			g.drawImage(eny.EnemyImg,eny.Ex,eny.Ey,null);
		}
	}
	//�л��Ķ�̬�仯
	public void changeEnemy()
	{
		for(int i=0;i<enemys.size();i++)
		{
			enemys.get(i).step();
		}
	}
	//����Խ������
	public void outOfBounds()
	{
		//����Խ��Ķ���
		//����Խ���ӵ�
		for(int i=0;i<bullets.size();i++)
		{
			Bullet b = bullets.get(i);
			if(b.outOfBounds())
			bullets.remove(i);
		}
		//����Խ��л�
		for(int i=0;i<enemys.size();i++)
		{
			Enemy e = enemys.get(i);
			if(e.outOfBounds())
			enemys.remove(i);
		}
		//����Խ��С����
		for(int i=0;i<redHearts.size();i++)
		{
			RedHearts r = redHearts.get(i);
			if(r.outOfBounds())
			redHearts.remove(i);
		}	
		//����Խ��Boss��
		if(bosses[1]!=null)
		if(bosses[1].blood<=0)
		{
			bosses[0]=null;bosses[1]=null;
		}
	}
	//����ӵ���ײ
	public void impact()
	{
		for(int i=0;i<bullets.size();i++)
		{
			//���Ӣ�ۻ��ӵ���ײ�л�
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
		//���л��ӵ���ײӢ�ۻ�
			if(bullets.get(i).impact(hero)&&bullets.get(i).type==1)
			{
				bullets.remove(i);
				hero.blood-=5;
			}
		}
		for(int i=0;i<bullets.size();i++){
			//���boss���ӵ���ײӢ�ۻ�
				if(bullets.get(i).impact(hero)&&bullets.get(i).type==2)
				{
					bullets.remove(i);
					hero.blood-=5;
				}
			}
		if(bosses[1]!=null)
		for(int i=0;i<bullets.size();i++){
			//���Ӣ�ۻ����ӵ���ײboss����������
				if(bullets.get(i).impact(bosses[1])&&bullets.get(i).type==0)
				{
					bullets.remove(i);
					bosses[1].blood-=5;
				}
			}
	}
	//����Ƿ�ԳԵ�С����
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
	//����Boss
	public void actionBoss()
	{
		if(score!=0&&score%1000==0&&bosses[0]==null)
			bosses[0] = new Boss(1);
		if(bosses[0]!=null&&bosses[0].outOfBound()&&bosses[1]==null)
		{
			bosses[1] = new Boss(0);
		}
	}
	//�ƶ�boss��
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
	//��boss��
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
			g.setFont(new Font("����",Font.BOLD,25));
			g.setColor(Color.YELLOW);
			g.drawString("BossѪ��:"+bosses[1].blood,170,30);
		}
			
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//������Ӣ�ۻ�
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		hero.Hx = e.getX()-hero.HeroImg.getWidth(null)/2;
		hero.Hy = e.getY()-hero.HeroImg.getHeight(null)/2;	
	}
}

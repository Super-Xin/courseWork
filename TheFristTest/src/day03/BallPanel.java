package day03;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class BallPanel extends JPanel implements MouseMotionListener,MouseListener{
	//构造面板
	int x=60,hight=10,width=500,flag=0;
	int score = 0;
	public static final int BallNum=1;
	Ball[] ball = new Ball[BallNum];
	Brick[] brick = new Brick[10];
	public BallPanel()
	{
		//鼠标事件的注册监听
		addMouseListener(this);
		addMouseMotionListener(this);
		for(int i=0;i<ball.length;i++)
		{
			ball[i] = new Ball();
		}
		//判断砖块是否重合，重合重新生成
		for(int i=0;i<brick.length;i++)
		{
			brick[i]=new Brick();
			if(i>0)
			for(int j=0;j<i;j++) {
				if(new Rect_judge(brick[i],brick[j]).get_res()){
				i--;
				break;
					}
				}
		}
		setBackground(Color.YELLOW);
	}
	public void paint(Graphics g){
		super.paint(g);
		//设置小球
		for(int i=0;i<ball.length;i++){
		g.setColor(ball[i].color);
		g.fillOval(ball[i].x, ball[i].y,ball[i].width,ball[i].width);
		}
		//设置砖块
		for(int i=0;i<brick.length;i++){
		g.setColor(brick[i].color);
		g.fillRect(brick[i].x, brick[i].y,brick[i].width,brick[i].height);
		}
		//设置挡板
		g.setColor(Color.BLACK);
		g.fillRect(x,BallFrame.HEIGHT-200, width, hight);
		//判断游戏状态
		if(flag==1)
		{
			g.setFont(new Font("黑体",Font.BOLD,200));
			g.setColor(Color.BLACK);
			g.drawString("Game Over",BallFrame.WIDTH/2-500, BallFrame.HEIGHT/2);
		}
		if(flag==2)
		{
			g.setFont(new Font("黑体",Font.BOLD,200));
			g.setColor(Color.BLACK);
			g.drawString("You Win",BallFrame.WIDTH/2-400, BallFrame.HEIGHT/2);
		}
		//设置得分
		g.setFont(new Font("华文行楷",Font.BOLD,40));
		g.setColor(Color.BLACK);
		g.drawString("得分："+score,1350,800);
	}
	public void action(){
	final Timer timer = new Timer();
	timer.schedule(new TimerTask(){
		public void run(){
			//更改小球坐标
			//判断小球状态
			//做循环形成不同小球
		for(int i=0;i<ball.length;i++){
			//状态0
			if(ball[i].state==0)
			{
				ball[i].x+=ball[i].speed;ball[i].y-=ball[i].speed;
			}
			//状态1
			if(ball[i].state==1)
			{
				ball[i].x+=ball[i].speed;ball[i].y+=ball[i].speed;
			}
			//状态2
			if(ball[i].state==2)
			{
				ball[i].x-=ball[i].speed;ball[i].y+=ball[i].speed;
			}
			//状态3
			if(ball[i].state==3)
			{
				ball[i].x-=ball[i].speed;ball[i].y-=ball[i].speed;
			}
			//判断小球碰撞
			//右边界
			if(ball[i].x+ball[i].width+15>=BallFrame.WIDTH)
			{
				//判断小球之前状态
				if(ball[i].state==0)
					ball[i].state=3;
				if(ball[i].state==1)
					ball[i].state=2;
			}
			//下边界
			if(ball[i].y+ball[i].width+45>=BallFrame.HEIGHT)
			{
				//判断小球状态
				if(ball[i].state==1)
					ball[i].state=0;
				if(ball[i].state==2)
					ball[i].state=3;
				flag=1;
				timer.cancel();
			}
			//左边界
			if(ball[i].x<=0){
				//判断状态
				if(ball[i].state==3)
					ball[i].state=0;
				if(ball[i].state==2)
					ball[i].state=1;
			}
			//上边界
			if(ball[i].y<=0)
			{
				//判断状态
				if(ball[i].state==3)
					ball[i].state=2;
				if(ball[i].state==0)
					ball[i].state=1;
			}
			//判断小球碰到挡板
			if((ball[i].x+ball[i].width/2)>=x&&
					(ball[i].x+ball[i].width/2)<=x+width&&
					(ball[i].y+ball[i].width)>=BallFrame.HEIGHT-200&&
					ball[i].y+ball[i].width<=BallFrame.HEIGHT-200+hight)
			{
				if(ball[i].state==2)
				ball[i].state=3;
				if(ball[i].state==1)
				ball[i].state=0;	
				score+=10;
			}
			//判断小球碰到砖块
			for(int j=0;j<brick.length;j++)
			{
				if(brick[j].height==0)
				{
					continue;
				}
				//碰到砖块上边界
				if(ball[i].x+ball[i].width/2>brick[j].x&&
						ball[i].x+ball[i].width/2<brick[j].x+brick[j].width&&
						ball[i].y+ball[i].width>brick[j].y&&
						ball[i].y+ball[i].width<brick[j].y+brick[j].height)
				{
					if(ball[i].state==1)
					{
						ball[i].state=0;
						brick[j].height_minus();
						score+=20;
					}
					if(ball[i].state==2){
						ball[i].state=3;
						brick[j].height_minus();
						score+=20;
					}
					
				}
				//碰到砖块下边界
				if(ball[i].x+ball[i].width/2>brick[j].x&&
						ball[i].x+ball[i].width/2<brick[j].x+brick[i].width&&
						ball[i].y<brick[j].y+brick[j].height&&
						ball[i].y>brick[j].y)
				{
					//判断状态
					if(ball[i].state==3)
					{
						ball[i].state=2;
						brick[j].height_minus();
						score+=20;
					}
					if(ball[i].state==0)
					{
						ball[i].state=1;
						brick[j].height_minus();
						score+=20;
					}
				}
			}
			//判断赢
			for(int k=0,m=0;k<brick.length;k++)
			{
				if(brick[k].height<=0)
					m++;
				if(m==brick.length)
				{
					flag=2;
					timer.cancel();
				}
			}
	}
			//重汇（刷新）
			repaint();
		}
	},1500,1);
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		  if(e.getButton()==1||e.getButton()==2||e.getButton()==3)
		  {
			  //点击鼠标左键或者右键游戏继续
			  if(flag!=0){
				flag=0;
				BallFrame.main(null);
				}
		 }
	}
	@Override
	public void mouseDragged(MouseEvent e) {

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		x=e.getX()-width/2;
		//挡板碰到左边界
		if(x<=0)
		x=0;
		//挡板碰到右边界
		if(x+width+15>BallFrame.WIDTH)
		x=BallFrame.WIDTH-width-15;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
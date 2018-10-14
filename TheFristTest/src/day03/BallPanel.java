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
	//�������
	int x=60,hight=10,width=500,flag=0;
	int score = 0;
	public static final int BallNum=1;
	Ball[] ball = new Ball[BallNum];
	Brick[] brick = new Brick[10];
	public BallPanel()
	{
		//����¼���ע�����
		addMouseListener(this);
		addMouseMotionListener(this);
		for(int i=0;i<ball.length;i++)
		{
			ball[i] = new Ball();
		}
		//�ж�ש���Ƿ��غϣ��غ���������
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
		//����С��
		for(int i=0;i<ball.length;i++){
		g.setColor(ball[i].color);
		g.fillOval(ball[i].x, ball[i].y,ball[i].width,ball[i].width);
		}
		//����ש��
		for(int i=0;i<brick.length;i++){
		g.setColor(brick[i].color);
		g.fillRect(brick[i].x, brick[i].y,brick[i].width,brick[i].height);
		}
		//���õ���
		g.setColor(Color.BLACK);
		g.fillRect(x,BallFrame.HEIGHT-200, width, hight);
		//�ж���Ϸ״̬
		if(flag==1)
		{
			g.setFont(new Font("����",Font.BOLD,200));
			g.setColor(Color.BLACK);
			g.drawString("Game Over",BallFrame.WIDTH/2-500, BallFrame.HEIGHT/2);
		}
		if(flag==2)
		{
			g.setFont(new Font("����",Font.BOLD,200));
			g.setColor(Color.BLACK);
			g.drawString("You Win",BallFrame.WIDTH/2-400, BallFrame.HEIGHT/2);
		}
		//���õ÷�
		g.setFont(new Font("�����п�",Font.BOLD,40));
		g.setColor(Color.BLACK);
		g.drawString("�÷֣�"+score,1350,800);
	}
	public void action(){
	final Timer timer = new Timer();
	timer.schedule(new TimerTask(){
		public void run(){
			//����С������
			//�ж�С��״̬
			//��ѭ���γɲ�ͬС��
		for(int i=0;i<ball.length;i++){
			//״̬0
			if(ball[i].state==0)
			{
				ball[i].x+=ball[i].speed;ball[i].y-=ball[i].speed;
			}
			//״̬1
			if(ball[i].state==1)
			{
				ball[i].x+=ball[i].speed;ball[i].y+=ball[i].speed;
			}
			//״̬2
			if(ball[i].state==2)
			{
				ball[i].x-=ball[i].speed;ball[i].y+=ball[i].speed;
			}
			//״̬3
			if(ball[i].state==3)
			{
				ball[i].x-=ball[i].speed;ball[i].y-=ball[i].speed;
			}
			//�ж�С����ײ
			//�ұ߽�
			if(ball[i].x+ball[i].width+15>=BallFrame.WIDTH)
			{
				//�ж�С��֮ǰ״̬
				if(ball[i].state==0)
					ball[i].state=3;
				if(ball[i].state==1)
					ball[i].state=2;
			}
			//�±߽�
			if(ball[i].y+ball[i].width+45>=BallFrame.HEIGHT)
			{
				//�ж�С��״̬
				if(ball[i].state==1)
					ball[i].state=0;
				if(ball[i].state==2)
					ball[i].state=3;
				flag=1;
				timer.cancel();
			}
			//��߽�
			if(ball[i].x<=0){
				//�ж�״̬
				if(ball[i].state==3)
					ball[i].state=0;
				if(ball[i].state==2)
					ball[i].state=1;
			}
			//�ϱ߽�
			if(ball[i].y<=0)
			{
				//�ж�״̬
				if(ball[i].state==3)
					ball[i].state=2;
				if(ball[i].state==0)
					ball[i].state=1;
			}
			//�ж�С����������
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
			//�ж�С������ש��
			for(int j=0;j<brick.length;j++)
			{
				if(brick[j].height==0)
				{
					continue;
				}
				//����ש���ϱ߽�
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
				//����ש���±߽�
				if(ball[i].x+ball[i].width/2>brick[j].x&&
						ball[i].x+ball[i].width/2<brick[j].x+brick[i].width&&
						ball[i].y<brick[j].y+brick[j].height&&
						ball[i].y>brick[j].y)
				{
					//�ж�״̬
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
			//�ж�Ӯ
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
			//�ػ㣨ˢ�£�
			repaint();
		}
	},1500,1);
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		  if(e.getButton()==1||e.getButton()==2||e.getButton()==3)
		  {
			  //��������������Ҽ���Ϸ����
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
		//����������߽�
		if(x<=0)
		x=0;
		//���������ұ߽�
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
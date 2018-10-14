package GoBangBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GoBangPanel extends JPanel implements MouseListener{
	Image bgimg = new ImageIcon("GoBangImg/bgimg.jpg").getImage();
	//�ж��ǲ��ǰ���
	public boolean isWhite = false;
	//�ж��Ƿ�Ӯ
	public boolean isWin = false;
	//�жϰ�����ߺ���Ӯ
	public int whiteOrBlake;
	//��������
	ArrayList<ChessBoard> chessBoards = new ArrayList<ChessBoard>();
	//��Ч����
	Music music = new Music();
	public GoBangPanel()
	{
		//setBackground(Color.WHITE);
		addMouseListener(this);
		music.startBgMusic();
		setFocusable(true);
	}
	
	//���û���
	public void paint(Graphics g)
	{
		super.paint(g);
		//������ͼƬ
		g.drawImage(bgimg, 0, 0, 900, 900, null);
		//������
		drawline(g);
		//������
		piantChessBoard(g);
		if(isWin)
		{
			g.setFont(new Font("����", Font.BOLD, 25));
			if(whiteOrBlake==1)
			g.drawString("��Ϸ����������Ӯ", 630, 30);
			else if(whiteOrBlake==2)
			g.drawString("��Ϸ����������Ӯ", 630, 30);
		}
		//����ǰ����״̬
		g.setFont(new Font("����", Font.BOLD, 25));
		if(isWhite)
			g.drawString("��һ��������", 40, 30);
		else
			g.drawString("��һ��������", 40, 30);
		//�����尴ť
		piantRemove(g);
	}
	//������
	public void drawline(Graphics g)
	{
		//��������ɫ
		g.setColor(Color.YELLOW);
		//g.fillRect(40, 40, 800, 800);
		g.setColor(Color.BLACK);
		//������
		for(int i=0;i<=20;i++){
			g.drawLine(40,40+(800/20*i),840,40+(800/20*i));
		//������
			g.drawLine(40+(800/20*i),40,40+(800/20*i),840);
		}
	}
	//������
	public void piantChessBoard(Graphics g)
	{
		for(int i=0;i<chessBoards.size();i++)
		{
			ChessBoard c = chessBoards.get(i);
			g.drawImage(c.bImg, c.Px, c.Py, 30, 30,null);
		}
	}
	//���尴ť
	public void piantRemove(Graphics g)
	{
		g.drawString("��ʾ����˿��Խ��л���", 280, 30);
	}
	//�������ȡ���λ�ã����½����ӷ�������
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ChessBoard chessBoard;
		//��¼�Ƿ��ظ�
		boolean isTure = false;
		if(isWhite)
		{
			chessBoard = new ChessBoard(e.getX(), e.getY(), 0);
			isWhite = false;
		}
		else
		{
			chessBoard = new ChessBoard(e.getX(), e.getY(), 1);
			isWhite = true;
		}
		//����ȫ�����ӣ��������ظ���
		for(int i=0;i<chessBoards.size();i++)
		{
			if(chessBoard.Bx==chessBoards.get(i).Bx&&
					chessBoard.By==chessBoards.get(i).By)
			{
				isTure=true;
			}
		}
		//�ж������Ƿ�Խ��
		if(chessBoard.Bx>840||chessBoard.Bx<40||
					chessBoard.By<40||chessBoard.By>840)
		{
			isTure=true;
		}
		if(!isTure&&!isWin){
			chessBoards.add(chessBoard);
			whiteOrBlake=judgeWin(chessBoard);
			music.startDownBoard();
			repaint();
		}
		//�жϻ���
		if(chessBoards.size()!=0)
		if(e.getX()>=270&&e.getX()<=570&&e.getY()>=0&&e.getY()<=20)
		{
			if(isWin)
			{
				isWin=false;
				music.startBgMusic();
			}
			chessBoards.remove(chessBoards.size()-1);
			repaint();
		}
	}
	//�����ж��Ƿ�Ӯ
	public int judgeWin(ChessBoard c)
	{
		//����ֵ1����Ӯ��2����Ӯ��0��Ϸ����
		//���·������ж�
		int upDown = 0;
		int downCount = 0;
		int upCount = 0;
		//���ұ����ж�
		int leftRight = 0;
		int leftCount = 0;
		int rightCount = 0;
		//�������±����ж�
		int leftUpRightDown = 0;
		int leftUpCount = 0;
		int rightDownCount = 0;
		//�������ϱ����ж�
		int leftDownRightUp = 0;
		int leftDownCount = 0;
		int rightUpCount = 0;
		//��ʼ�ж�
		for(int i=1;i<=4;i++)
		{
			for(int j=0;j<chessBoards.size();j++)
			{
				if(c.type==chessBoards.get(j).type)
				{
					if(c.Bx==chessBoards.get(j).Bx)
					{
					//�Ϸ��ж�
							if(c.By==chessBoards.get(j).By+40*i)
							{
								if(upCount==i-1)
								{
									upDown++;
									upCount++;
								}
							}
					//�·��ж�
						if(c.By==chessBoards.get(j).By-40*i)
						{
							if(downCount==i-1)
							{
								upDown++;
								downCount++;
							}
						}
					}
					if(c.By==chessBoards.get(j).By)
					{
					//���ж�
						if(c.Bx==chessBoards.get(j).Bx+40*i)
						{
							if(leftCount==i-1)
							{
								leftRight++;
								leftCount++;
							}
						};
					//�ҷ��ж�
						if(c.Bx==chessBoards.get(j).Bx-40*i)
						{
							if(rightCount==i-1)
							{
								leftRight++;
								rightCount++;
							}
						}
					}
					//�����ж�
					if(c.Bx==chessBoards.get(j).Bx+40*i&&c.By==chessBoards.get(j).By+40*i)
					{
						if(leftUpCount==i-1)
						{
							leftUpRightDown++;
							leftUpCount++;
						}
					}
					//�����ж�
					if(c.Bx==chessBoards.get(j).Bx-40*i&&c.By==chessBoards.get(j).By-40*i)
					{
						if(rightDownCount==i-1)
						{
							leftUpRightDown++;
							rightDownCount++;
						}
					}
					//�����ж�
					if(c.Bx==chessBoards.get(j).Bx+40*i&&c.By==chessBoards.get(j).By-40*i)
					{
						if(leftDownCount==i-1)
						{
							leftDownRightUp++;
							leftDownCount++;
						}
					}
					//�����ж�
					if(c.Bx==chessBoards.get(j).Bx-40*i&&c.By==chessBoards.get(j).By+40*i)
					{
						if(rightUpCount==i-1)
						{
							leftDownRightUp++;
							rightUpCount++;
						}
					}
				}
			}		
		}
		//���ս���ж�
		if((upDown>=4||leftRight>=4||leftUpRightDown>=4||leftDownRightUp>=4)&&c.type==0)
		{
			isWin = true;
			music.stopBgMusic();
			music.startWin();
			return 1;
		}
		if((upDown>=4||leftRight>=4||leftUpRightDown>=4||leftDownRightUp>=4)&&c.type==1){
			isWin = true;
			music.stopBgMusic();
			music.startWin();
			return 2;
		}
			return 0;
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

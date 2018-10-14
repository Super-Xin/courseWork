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
	//判断是不是白棋
	public boolean isWhite = false;
	//判断是否赢
	public boolean isWin = false;
	//判断白棋或者黑棋赢
	public int whiteOrBlake;
	//旗子容器
	ArrayList<ChessBoard> chessBoards = new ArrayList<ChessBoard>();
	//音效处理
	Music music = new Music();
	public GoBangPanel()
	{
		//setBackground(Color.WHITE);
		addMouseListener(this);
		music.startBgMusic();
		setFocusable(true);
	}
	
	//设置画笔
	public void paint(Graphics g)
	{
		super.paint(g);
		//画背景图片
		g.drawImage(bgimg, 0, 0, 900, 900, null);
		//画横线
		drawline(g);
		//画棋子
		piantChessBoard(g);
		if(isWin)
		{
			g.setFont(new Font("宋体", Font.BOLD, 25));
			if(whiteOrBlake==1)
			g.drawString("游戏结束：白棋赢", 630, 30);
			else if(whiteOrBlake==2)
			g.drawString("游戏结束：黑棋赢", 630, 30);
		}
		//画当前棋子状态
		g.setFont(new Font("宋体", Font.BOLD, 25));
		if(isWhite)
			g.drawString("下一步：白棋", 40, 30);
		else
			g.drawString("下一步：黑棋", 40, 30);
		//画悔棋按钮
		piantRemove(g);
	}
	//画棋盘
	public void drawline(Graphics g)
	{
		//画背景颜色
		g.setColor(Color.YELLOW);
		//g.fillRect(40, 40, 800, 800);
		g.setColor(Color.BLACK);
		//画横线
		for(int i=0;i<=20;i++){
			g.drawLine(40,40+(800/20*i),840,40+(800/20*i));
		//画竖线
			g.drawLine(40+(800/20*i),40,40+(800/20*i),840);
		}
	}
	//画旗子
	public void piantChessBoard(Graphics g)
	{
		for(int i=0;i<chessBoards.size();i++)
		{
			ChessBoard c = chessBoards.get(i);
			g.drawImage(c.bImg, c.Px, c.Py, 30, 30,null);
		}
	}
	//悔棋按钮
	public void piantRemove(Graphics g)
	{
		g.drawString("提示：点此可以进行悔棋", 280, 30);
	}
	//鼠标点击获取鼠标位置，并新建旗子放入棋盘
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ChessBoard chessBoard;
		//记录是否重复
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
		//遍历全部棋子，不允许重复下
		for(int i=0;i<chessBoards.size();i++)
		{
			if(chessBoard.Bx==chessBoards.get(i).Bx&&
					chessBoard.By==chessBoards.get(i).By)
			{
				isTure=true;
			}
		}
		//判断棋子是否越界
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
		//判断悔棋
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
	//暴力判断是否赢
	public int judgeWin(ChessBoard c)
	{
		//返回值1白棋赢，2黑棋赢，0游戏继续
		//上下方暴力判断
		int upDown = 0;
		int downCount = 0;
		int upCount = 0;
		//左右暴力判断
		int leftRight = 0;
		int leftCount = 0;
		int rightCount = 0;
		//左上右下暴力判断
		int leftUpRightDown = 0;
		int leftUpCount = 0;
		int rightDownCount = 0;
		//左下右上暴力判断
		int leftDownRightUp = 0;
		int leftDownCount = 0;
		int rightUpCount = 0;
		//开始判断
		for(int i=1;i<=4;i++)
		{
			for(int j=0;j<chessBoards.size();j++)
			{
				if(c.type==chessBoards.get(j).type)
				{
					if(c.Bx==chessBoards.get(j).Bx)
					{
					//上方判断
							if(c.By==chessBoards.get(j).By+40*i)
							{
								if(upCount==i-1)
								{
									upDown++;
									upCount++;
								}
							}
					//下方判断
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
					//左方判断
						if(c.Bx==chessBoards.get(j).Bx+40*i)
						{
							if(leftCount==i-1)
							{
								leftRight++;
								leftCount++;
							}
						};
					//右方判断
						if(c.Bx==chessBoards.get(j).Bx-40*i)
						{
							if(rightCount==i-1)
							{
								leftRight++;
								rightCount++;
							}
						}
					}
					//左上判断
					if(c.Bx==chessBoards.get(j).Bx+40*i&&c.By==chessBoards.get(j).By+40*i)
					{
						if(leftUpCount==i-1)
						{
							leftUpRightDown++;
							leftUpCount++;
						}
					}
					//右下判断
					if(c.Bx==chessBoards.get(j).Bx-40*i&&c.By==chessBoards.get(j).By-40*i)
					{
						if(rightDownCount==i-1)
						{
							leftUpRightDown++;
							rightDownCount++;
						}
					}
					//左下判断
					if(c.Bx==chessBoards.get(j).Bx+40*i&&c.By==chessBoards.get(j).By-40*i)
					{
						if(leftDownCount==i-1)
						{
							leftDownRightUp++;
							leftDownCount++;
						}
					}
					//右上判断
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
		//最终结果判断
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

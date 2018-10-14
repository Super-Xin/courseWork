package GoBangBoard;

import java.awt.Image;

import javax.swing.ImageIcon;

//旗子类
public class ChessBoard {
	//棋子真实坐标
	int Bx;
	int By;
	//棋子画的坐标
	int Px;
	int Py;
	//旗子类型
	int type;//0白旗，1黑棋
	//静态载入图片
	static Image[] images = new Image[2];
	static{
		images[0] = new ImageIcon("GoBangImg/white.png").getImage();
		images[1] = new ImageIcon("GoBangImg/black.png").getImage();
	}
	//储存棋子图片
	Image bImg;
	public ChessBoard(int x,int y,int i)
	{
		this.type = i;
		//防止棋子漂移，粗略定点棋子位置
		this.Bx = x/40*40;
		this.By = y/40*40;
		//再次调整棋子位置
		if(x%40>=20)
		this.Bx+=40;
		if(y%40>=20)
		this.By+=40;
		bImg = images[type];
		Px = Bx-15;
		Py = By-15;
	}
}

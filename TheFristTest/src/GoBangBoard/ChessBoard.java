package GoBangBoard;

import java.awt.Image;

import javax.swing.ImageIcon;

//������
public class ChessBoard {
	//������ʵ����
	int Bx;
	int By;
	//���ӻ�������
	int Px;
	int Py;
	//��������
	int type;//0���죬1����
	//��̬����ͼƬ
	static Image[] images = new Image[2];
	static{
		images[0] = new ImageIcon("GoBangImg/white.png").getImage();
		images[1] = new ImageIcon("GoBangImg/black.png").getImage();
	}
	//��������ͼƬ
	Image bImg;
	public ChessBoard(int x,int y,int i)
	{
		this.type = i;
		//��ֹ����Ư�ƣ����Զ�������λ��
		this.Bx = x/40*40;
		this.By = y/40*40;
		//�ٴε�������λ��
		if(x%40>=20)
		this.Bx+=40;
		if(y%40>=20)
		this.By+=40;
		bImg = images[type];
		Px = Bx-15;
		Py = By-15;
	}
}

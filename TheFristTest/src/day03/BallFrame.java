package day03;

import javax.swing.JFrame;

public class BallFrame extends JFrame{
	//������Ϣ���ã�����
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	public BallFrame(){
		//���ñ���
		//���ô�С
		//���ÿɼ�
		//���ô���رգ��������
		setTitle("��ש��");
		setBounds(100, 50, WIDTH,HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		BallFrame chuangti = new BallFrame();
		BallPanel boli = new BallPanel();
		boli.action();
		chuangti.add(boli);
		}
}

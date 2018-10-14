package day03;

import javax.swing.JFrame;

public class BallFrame extends JFrame{
	//基本信息设置，构造
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	public BallFrame(){
		//设置标题
		//设置大小
		//设置可见
		//设置窗体关闭，程序结束
		setTitle("打砖块");
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

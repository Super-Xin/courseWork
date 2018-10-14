package Snake;

import javax.swing.JFrame;

public class snakeFrame extends JFrame {
	//静态变量
	static int index = 0;
	//全局变量定义
	snakeJpane boli;
	startPanel start;
	//构造函数
	public snakeFrame()
	{
		//设置标题
		setTitle("贪吃蛇——文达塑料兄dei队");
		//设置窗体大小
		setBounds(100, 100, 900, 720);
		//设置切换
		boli = new snakeJpane();
		step();
		//设置可见
		setVisible(true);
		//设置不允许最大化
		setResizable(false);
		//设置窗口关闭，程序关闭
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void step()
	{
		if(index==0)
		{
			start = new startPanel(this);
			add(start);
		}
		else
		{
			start.setVisible(false);
			addKeyListener(boli);
			boli.setVisible(true);
			boli.action();
			add(boli);
		}
	}
	public static void main(String[] args)
	{
		new snakeFrame();
	}
}
package day05;

import javax.swing.JFrame;
//雷霆战机窗口
public class PlaneFrame extends JFrame{
	public PlaneFrame(){
		PlanePanel boli = new PlanePanel();
		boli.action();
		add(boli);
		//设置标题
		setTitle("射击吧，兄弟");
		//设置窗体大小及位置
		setBounds(400, 100, 512, 768);
		//设置可见
		setVisible(true);
		//设置窗体关闭，程序结束运行
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置不允许最大化
		setResizable(false);
	}
	public static void main(String[] args)
	{
		new Music();
		PlaneFrame chuangti = new PlaneFrame();
	}
}

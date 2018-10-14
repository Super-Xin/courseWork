package day02;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{
	public MyJFrame(){
	//设置标题
	setTitle("Day02程序练习");
	//设置大小
	setSize(1200,800);
	//设置可见
	setVisible(true);
	//窗体关闭，后台随之关闭
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
package day01;
import javax.swing.JFrame;

public class StarFrame extends JFrame{
		//构造
		public StarFrame(){
			//设置窗体标题
			//设置窗体大小
			//设置窗体可见
			//窗体关闭，随之关闭
			setTitle("满天星");
			setSize(1920,1080);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	//程序入口
	public static void main(String[] args){
		StarFrame chuangti = new StarFrame();
		StarPanel boil = new StarPanel();
		boil.action();
		chuangti.add(boil);
	}
}

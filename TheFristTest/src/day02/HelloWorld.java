package day02;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class HelloWorld {
	//程序的入口;
	public static void main(String[] args){
		
		final MyJFrame chuangti = new MyJFrame();
		MyJPanel boil = new MyJPanel();
		//窗体上添加面板
		chuangti.add(boil);
	}
}
package day01;
import javax.swing.JFrame;

public class StarFrame extends JFrame{
		//����
		public StarFrame(){
			//���ô������
			//���ô����С
			//���ô���ɼ�
			//����رգ���֮�ر�
			setTitle("������");
			setSize(1920,1080);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	//�������
	public static void main(String[] args){
		StarFrame chuangti = new StarFrame();
		StarPanel boil = new StarPanel();
		boil.action();
		chuangti.add(boil);
	}
}

package day05;

import javax.swing.JFrame;
//����ս������
public class PlaneFrame extends JFrame{
	public PlaneFrame(){
		PlanePanel boli = new PlanePanel();
		boli.action();
		add(boli);
		//���ñ���
		setTitle("����ɣ��ֵ�");
		//���ô����С��λ��
		setBounds(400, 100, 512, 768);
		//���ÿɼ�
		setVisible(true);
		//���ô���رգ������������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ò��������
		setResizable(false);
	}
	public static void main(String[] args)
	{
		new Music();
		PlaneFrame chuangti = new PlaneFrame();
	}
}

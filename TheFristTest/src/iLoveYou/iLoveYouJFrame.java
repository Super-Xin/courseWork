package iLoveYou;

import javax.swing.JFrame;

public class iLoveYouJFrame extends JFrame {
		public iLoveYouJFrame() {
		//���ñ���
		setTitle("�װ��ģ��Ұ��㣡 By Xin");
		//���ô����С��λ��
		setBounds(450, 200, 600, 400);
		//���ô���رգ������������
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//���ò��������
		setResizable(false);
		iLoveYouJPanel jpanel =  new iLoveYouJPanel();
		add(jpanel);
		//���ÿɼ�
		setVisible(true);
	}
	 public static void main(String[] args) {
		new loveCuiMusic();
        new iLoveYouJFrame();
	}
}

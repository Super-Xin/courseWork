package iLoveYou;

import javax.swing.JFrame;

public class iLoveYouJFrame extends JFrame {
		public iLoveYouJFrame() {
		//设置标题
		setTitle("亲爱的，我爱你！ By Xin");
		//设置窗体大小及位置
		setBounds(450, 200, 600, 400);
		//设置窗体关闭，程序结束运行
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//设置不允许最大化
		setResizable(false);
		iLoveYouJPanel jpanel =  new iLoveYouJPanel();
		add(jpanel);
		//设置可见
		setVisible(true);
	}
	 public static void main(String[] args) {
		new loveCuiMusic();
        new iLoveYouJFrame();
	}
}

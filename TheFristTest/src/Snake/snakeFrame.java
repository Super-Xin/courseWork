package Snake;

import javax.swing.JFrame;

public class snakeFrame extends JFrame {
	//��̬����
	static int index = 0;
	//ȫ�ֱ�������
	snakeJpane boli;
	startPanel start;
	//���캯��
	public snakeFrame()
	{
		//���ñ���
		setTitle("̰���ߡ����Ĵ�������dei��");
		//���ô����С
		setBounds(100, 100, 900, 720);
		//�����л�
		boli = new snakeJpane();
		step();
		//���ÿɼ�
		setVisible(true);
		//���ò��������
		setResizable(false);
		//���ô��ڹرգ�����ر�
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
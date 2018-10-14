package GoBangBoard;

import javax.swing.JFrame;

public class GoBangFrame extends JFrame{

	public static final int XFrame = 885;
	public static final int YFrame = 915;
	static int index = 0;
	StartPanel startBoli;
	GoBangPanel boli; 
	public GoBangFrame(){
		//���ñ���
		setTitle("�����塪���Ĵ�������dei��                              �����ֵܣ��������ϳ���");
		//���ô����С��λ��
		setBounds(400, 100, XFrame, YFrame);
		//���ô���رգ������������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ò��������
		setResizable(false);
		//�����л�
		startBoli = new StartPanel(this);
		boli = new GoBangPanel();
		step();
		//���ÿɼ�
		setVisible(true);
	}
	public void step()
	{
		if(index==0)
		this.add(startBoli);
		else
		{
			startBoli.setVisible(false);
			this.add(boli);
		}
	}
	public static void main(String[] args)
	{
		new GoBangFrame();
	}

}

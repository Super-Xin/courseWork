package GoBangBoard;

import javax.swing.JFrame;

public class GoBangFrame extends JFrame{

	public static final int XFrame = 885;
	public static final int YFrame = 915;
	static int index = 0;
	StartPanel startBoli;
	GoBangPanel boli; 
	public GoBangFrame(){
		//设置标题
		setTitle("五子棋——文达塑料兄dei队                              塑料兄弟，不做塑料程序");
		//设置窗体大小及位置
		setBounds(400, 100, XFrame, YFrame);
		//设置窗体关闭，程序结束运行
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置不允许最大化
		setResizable(false);
		//窗体切换
		startBoli = new StartPanel(this);
		boli = new GoBangPanel();
		step();
		//设置可见
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

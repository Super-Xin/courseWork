package iLoveYou;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class iLoveYouJPanel extends JPanel implements ActionListener,MouseMotionListener{
	//全局变量
	int x = 375,y = 200;
	//按钮
	JButton love = new JButton("爱你");
	JButton noLove = new JButton("不爱"); 
	//判断位置
	boolean istrue = true;
	//构造函数
	public iLoveYouJPanel() {
		setLayout(null);
		MyJButton(x,y);
		love.addActionListener(this);
		noLove.addMouseMotionListener(this);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.setFont(new Font(null,Font.BOLD,30));
		g.drawString("亲爱的，我爱你，你爱我吗？", 90, 100);
		MyJButton(x,y);
	}
	public void MyJButton(int x,int y)
	{
		love.setBounds(110, 200, 100, 50);
		add(love);
		noLove.setBounds(x,y,100, 50);
		add(noLove);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,"宝贝翠翠，我也爱你哦！");
		Object[] options ={ "好的啦", "不要嘛" }; 
		int n = JOptionPane.showOptionDialog(this, "亲爱的翠翠，要不要退出呢？", "现在允许你关闭程序啦",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(n==0)
		{
			JOptionPane.showMessageDialog(this,"再见，么么哒！(￣３￣)a");
			System.exit(0);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"猪，别闹嘛！(=ﾟωﾟ)ﾉ");
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!istrue)
		{
			x=420;
			y=130;
			istrue = true;
			repaint();
		}
		else
		{
			x=375;
			y=200;
			istrue = false;
			repaint();
		}
}
}

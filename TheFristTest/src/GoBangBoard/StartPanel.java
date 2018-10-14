package GoBangBoard;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements MouseListener{
	GoBangFrame f;
	//±³¾°Í¼Æ¬
	Image image;
	public StartPanel(GoBangFrame f)
	{
		this.f=f;
		image = new ImageIcon("GoBangImg/start.png").getImage();
		addMouseListener(this);
	}
	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,900,900,null);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>=280&&e.getX()<=620&&e.getY()>=450&&e.getY()<=550)
		{
			f.index=1;
			f.step();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

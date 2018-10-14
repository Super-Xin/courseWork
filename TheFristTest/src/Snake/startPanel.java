package Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class startPanel extends JPanel implements MouseListener{
		snakeFrame f;
		//±³¾°Í¼Æ¬
		Image image;
		public startPanel(snakeFrame f)
		{
			this.f=f;
			image = new ImageIcon("snakeImg/startImg.png").getImage();
			addMouseListener(this);
		}
		public void paint(Graphics g)
		{
			super.paint(g);
			g.drawImage(image,-5,0,900,720,null);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getX()>=145&&e.getX()<=745&&e.getY()>=410&&e.getY()<=570)
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

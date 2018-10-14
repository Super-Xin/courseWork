package Snake;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class snakeFood {
	int x,y;
	Image foodImg = new ImageIcon("snakeimg/food.png").getImage();
	public snakeFood(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}

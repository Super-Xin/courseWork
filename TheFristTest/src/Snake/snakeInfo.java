package Snake;

import java.awt.Image;
import javax.swing.ImageIcon;

//蛇
public class snakeInfo {
	static Image snakeImg[] = new Image[5];
	static{
		snakeImg[0] = new ImageIcon("snakeimg/body.png").getImage();
		snakeImg[1] = new ImageIcon("snakeimg/up.png").getImage();
		snakeImg[2] = new ImageIcon("snakeimg/down.png").getImage();
		snakeImg[3] = new ImageIcon("snakeimg/left.png").getImage();
		snakeImg[4] = new ImageIcon("snakeimg/right.png").getImage();
	}
	int x[] = new int[750];
	int y[] = new int[750];
	int len = 3;
	int state = 4; //1上，2下，3左，4右
	public snakeInfo()
	{
		x[0] = 100;
		y[0] = 100;
		x[1] = 75;
		y[1] = 100;
		x[2] = 50;
		y[2] = 100;
	}
	//判断是否吃到食物
	public boolean isEatFood(snakeFood f)
	{
		if(x[0]==f.x&&y[0]==f.y)
			return true;
		else
			return false;
	}
	//判断是否死亡
	public boolean isDeath()
	{
		for(int i=1;i<len;i++)
			if(x[0]==x[i]&&y[0]==y[i])
				return true;
		if(x[0]>850||x[0]<25||y[0]>650||y[0]<75)
			return true;
		return false;
	}
}

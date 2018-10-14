package day03;

public class Rect_judge {
	private boolean res;
	public Rect_judge(Brick a1,Brick a2)
	{
		//判断是否重合
		//左上点
		if(a1.x>=a2.x&&a1.x<=a2.x+a2.width&&a1.y>=a2.y&&a1.y<=a2.y+a2.height)
			res = true;
		//右上点
		if(a1.x+a1.width>=a2.x&&a1.x+a1.width<=a2.x+a2.width&&a1.y>=a2.y&&a1.y<=a2.y+a2.height)
			res = true;
		//左下点
		if(a1.x>=a2.x&&a1.x<=a2.x+a2.width&&a1.y+a1.height>=a2.y&&a1.y+a1.height<=a2.y+a2.height)
			res = true;
		//右下点
		if(a1.x+a1.width>=a2.x&&a1.x+a1.width<=a2.x+a2.width&&a1.y+a1.height>=a2.y&&a1.y+a1.height<=a2.y+a2.height)
			res = true;
	}
	public boolean get_res()
	{
		return res;
	}
}
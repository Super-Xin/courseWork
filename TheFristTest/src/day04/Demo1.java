package day04;

import java.util.Scanner;

public class Demo1 {
	public static void main(String[] args)
	{
		Scanner sc =new Scanner(System.in);
		System.out.print("请输入年月日");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		System.out.println();
		System.out.print("你输入的是");
		System.out.println(year+"年-"+month+"月-"+day+"日");
	}
}

package com.jesu.tank;

public class MathTest {

	/**
	 * 指数函数（乘方，次方），y=a^x(a>0&a!=1),a为底数，x为次方（指数），y为幂（y>0） x=0,y=1
	 * 
	 * 对数函数，y=log(a)x(a>0&a!=1),a为底数，x为真数（x>0)，y为以a为底数x的对数（正数才有对数），lg以10为底数，ln以e为底数 x=1,y=0
	 * 
	 * 换底公式：log(a)x= log(b)x/log(b)a
	 * 
	 */
	public static void testLog() {
		System.out.println(Math.pow(2, -3));
		System.out.println(Math.E);
		System.out.println(Math.log(10));// 以Math.E为底数
		System.out.println(Math.log10(100));// 以10为底数
		System.out.println(Math.pow(Math.E, 2.302585092994046));
		System.out.println(Math.log10(256) / Math.log10(4));// 换底公式 log(4)256
	}

	public static void test(double angdeg) {
		double radian = Math.toRadians(angdeg);
		System.out.println(radian);
		int length = 10;
		double x1 = Math.cos(radian) * length;
		double y1 = Math.sin(radian) * length;
		System.out.println("角度：" + angdeg + "，" + x1 + " " + y1);
	}

	/**
	 * 三角函数
	 */
	public static void testSin() {
		double x = 0;
		double y = 0;
		double x11 = -7.071067811865474;
		double y11 = -7.071067811865474;
		double direction = Math.toDegrees(Math.atan((y11 - y) / (x11 - x)));
		double direction1 = Math.toDegrees(Math.atan2(y11 - y, x11 - x));
		System.out.println(direction);
		System.out.println(direction1);
		test(direction);
		test(direction1);

	}

	public static void main(String[] args) {
//		MathTest.testSin();
		double x1 = Math.cos(0) * 10;
		double y1 = Math.sin(0) * 10;
		System.out.println(x1+" "+y1);
	}
}

package com.atguigu.aop;

/**
 * 业务类
 * @author Dream
 *
 */
public class MathCalculator {

	public int div(int i, int j) {
		System.out.println("MathCalculator...div...");
		return i/j;
	}
}

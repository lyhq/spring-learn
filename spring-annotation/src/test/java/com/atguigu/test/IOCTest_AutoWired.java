package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigOfAutoWired;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;

public class IOCTest_AutoWired {

	// 容器创建
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			MainConfigOfAutoWired.class);

	@Test
	public void test01() {
//		printBeans(applicationContext);
		System.out.println("==========================");
		BookService bookService = (BookService) applicationContext.getBean(BookService.class);
		System.out.println(bookService);
		
//		BookDao bookDao = applicationContext.getBean(BookDao.class);
//		System.out.println(bookDao);
		
		Boss boss = applicationContext.getBean(Boss.class);
		System.out.println(boss);
		Car car = applicationContext.getBean(Car.class);
		System.out.println(car);
		
		Color color = applicationContext.getBean(Color.class);
		System.out.println(color);
		
		System.out.println(applicationContext);
		// 关闭容器
		applicationContext.close();
	}

	public void printBeans(ApplicationContext applicationContext) {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}

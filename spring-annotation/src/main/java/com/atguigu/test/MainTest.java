package com.atguigu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
		Person bean = ctx.getBean(Person.class);
		System.out.println(bean);
		
		String[] beanNamesForType = ctx.getBeanNamesForType(Person.class);
		for (String string : beanNamesForType) {
			System.out.println(string);
		}
	}
}

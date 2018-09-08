package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfPropertyValues;

public class IOCTest_PropertyValues {

	// 容器创建
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			MainConfigOfPropertyValues.class);

	@Test
	public void test01() {
		printBeans(applicationContext);
		System.out.println("==========================");
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
		
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
		
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

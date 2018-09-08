package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;

public class IOCTest {
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

	@Test
	public void testImport(){
		printBeans(applicationContext);
		
		//获取colorFactoryBean的类型
		//工厂Bean获取的是调用getObject得到的对象
		Object bean = applicationContext.getBean("colorFactoryBean");
		Object bean2 = applicationContext.getBean("colorFactoryBean");
		System.out.println("bean的类型："+bean.getClass());
		System.out.println(bean==bean2);
		
		Object bean3 = applicationContext.getBean("&colorFactoryBean");
		System.out.println(bean3.getClass());
	}
	
	public void printBeans(ApplicationContext applicationContext){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test03(){
		Environment environment = applicationContext.getEnvironment();
		//获取环境变量的值
		String property = environment.getProperty("os.name");
		System.out.println(property);
		
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test02(){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String string : beanDefinitionNames) {
			System.out.println(string);
		}
		
		Object person1 = applicationContext.getBean("person");
		Object person2 = applicationContext.getBean("person");
		System.err.println(person1 == person2);
	}
	
	@Test
	@SuppressWarnings("resource")
	public void test01(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String string : beanDefinitionNames) {
			System.out.println(string);
			
		}
	}
}

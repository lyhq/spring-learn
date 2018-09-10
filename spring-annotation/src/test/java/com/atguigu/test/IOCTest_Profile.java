package com.atguigu.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Yellow;
import com.atguigu.config.MainConfigOfProfile;

public class IOCTest_Profile {


	// 1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
	// 2、代码的方式激活某种环境；
	@Test
	public void test01() {
		// 容器创建
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//				MainConfigOfProfile.class);
		//1、创建一个applicationContext
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		//2、设置激活的环境
		applicationContext.getEnvironment().setActiveProfiles("dev");
		//3、注册主配置类
		applicationContext.register(MainConfigOfProfile.class);
		//4、刷新容器
		applicationContext.refresh();
		
		String[] strings = applicationContext.getBeanNamesForType(DataSource.class);
		for (String string : strings) {
			System.out.println(string);
		}
		
		Yellow yellow = applicationContext.getBean(Yellow.class);
		System.out.println(yellow);
		
		// 关闭容器
		applicationContext.close();
	}

}

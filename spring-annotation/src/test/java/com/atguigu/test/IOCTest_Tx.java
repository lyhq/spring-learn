package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;

public class IOCTest_Tx {

	@Test
	public void test01() {
		//容器创建
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(TxConfig.class);
		
		UserService userService = applicationContext.getBean(UserService.class);
		userService.insertUser();
		
		//关闭容器
		applicationContext.close();
	}
}

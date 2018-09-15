package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.ext.ExtConfig;

public class IOCTest_Ext {

	@SuppressWarnings("serial")
	@Test
	public void test01() {
		//容器创建
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ExtConfig.class);
		
		//自定义发布事件
		applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
		});
		
		//关闭容器
		applicationContext.close();
	}
}

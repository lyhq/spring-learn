package com.atguigu.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware {
	//注入ioc容器
	private ApplicationContext applicationContext = null;

	public Dog() {
		System.out.println("dog constructor...");
	}
	
	//在对象创建并赋值之后
	@PostConstruct
	public void init() {
		System.out.println("dog... @PostConstruct...");
	}
	
	//在容器移除对象之前
	@PreDestroy
	public void destory() {
		System.out.println("dog... @PreDestroy...");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}

package com.atguigu.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Dog {

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
}

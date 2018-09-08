package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

	//创建一个Color对象，这个对象会添加到容器中
	@Override
	public Color getObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ColorFactoryBean...getObject...");
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}

	//true:是单例，容器中就保存一份
	//false：不是单例，每次获取都是一个新的Bean
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}

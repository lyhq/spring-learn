package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Person;

//配置类 = 配置文件
@Configuration //告诉Spring这是一个配置类
@ComponentScans(value={
			@ComponentScan(value="com.atguigu", includeFilters={
				/*@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
				@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),*/
				@Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})
			}, useDefaultFilters=false),
			@ComponentScan(value="com.atguigu", includeFilters={
				@Filter(type=FilterType.ANNOTATION,classes={Service.class})
			}, useDefaultFilters=false)
})
//@ComponentScan(value="com.atguigu", /*excludeFilters={
//		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
//},*/ includeFilters={
//		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
//}, useDefaultFilters=false)
//@ComponentScan value 指定要扫描的包
//excludeFilters = Filter[]  指定扫描的时候按照什么规则进行排除某些组件
//includeFilters = Filter[]  指定扫描的时候按照什么规则进行筛选某些组件
//FilterType.ANNOTATION: 按照注解
//FilterType.ASSIGNABLE_TYPE: 按照给定的类型
//FilterType.ASPECTJ:ASPECTJ表达式
//FilterType.REGEX:正则表达式
//FilterType.CUSTOM:自定义
public class MainConfig {

	//给容器中注册一个bean,类型就是返回值的类型，id默认是方法名
	@Bean(name="person")
	public Person person01(){
		return new Person("zhangsan", 18);
	}
}

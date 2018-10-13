package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.atguigu.controller.MyFirstInterceptor;

//SpringMVC只扫描Controller, 子容器
//useDefaultFilters = false 禁用默认的过滤规则
@ComponentScan(value = "com.atguigu", includeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) }, useDefaultFilters = false)
@EnableWebMvc // @EnableWebMvc 实现SpringMVC的定制配置
public class AppConfig extends WebMvcConfigurerAdapter {

	// 定制

	// 视图解析器
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		// super.configureViewResolvers(registry);
		// 所有的页面都从/WEB-INF/ xx.jsp下找
		// registry.jsp();
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	//静态资源访问
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.configureDefaultServletHandling(configurer);
		configurer.enable();//相当于配置了 <mvc:default-servlet-handler />
	}
	
	//拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		super.addInterceptors(registry);
		registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");//拦截任意多层的路径
	}
}

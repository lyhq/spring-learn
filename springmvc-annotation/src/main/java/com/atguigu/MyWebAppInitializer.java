package com.atguigu;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.atguigu.config.AppConfig;
import com.atguigu.config.RootConfig;

/**
 * Web容器启动的时候创建对象，调用方法来初始化容器和前端控制器
 * @author Dream
 *
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 获取根容器的配置类，（相当于Spring的配置文件），父容器
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {RootConfig.class};
	}

	/**
	 * 获取Web容器的配置类，（相当于SpringMVC的配置文件），子容器
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {AppConfig.class};
	}

	/**
	 * 获取DispatcherServlet(前端控制器)的映射信息。
	 * "/":拦截所有请求（包括静态资源（xx.js, xx.png））,不包括xx.jsp;
	 * "/*":拦截所有请求，包括xx.jsp; jsp页面是tomcat的jsp引擎解析的。
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}

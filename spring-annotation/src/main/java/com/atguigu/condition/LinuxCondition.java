package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否Linux系统
 * @author Administrator
 *
 */
public class LinuxCondition implements Condition {

	/**
	 * ConditionContext： 判断条件可以使用的上下文环境
	 * AnnotatedTypeMetadata： 注释信息
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 判断是否Linux系统
		//1、获取当前ioc容器的bean工厂
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//2、获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		//3、获取当前运行的环境
		Environment environment = context.getEnvironment();
		//4、获取到bean注册的定义类
		BeanDefinitionRegistry registry = context.getRegistry();
		
		String property = environment.getProperty("os.name");
		if (property.contains("linux")) {
			return true;
		}
		return false;
	}

}

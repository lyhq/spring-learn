package com.atguigu.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.atguigu.bean.Car;

/**
 * Bean的生命周期
 *   bean的创建--bean的初始化--bean的销毁
 * 容器管理bean的生命周期
 *   
 * 我们可以自定义bean的初始化和销毁方法，容器在bean进行到当前生命周期的时候会调用我们自定义的初始化和销毁方法
 * 
 * 构造（创建对象）：
 *   	单实例：在容器启动的时候创建对象
 *   	多实例：在每次获取的时候创建对象
 *   
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 * 	 	对象创建完成，并赋值好，调用初始化方法。
 * BeanPostProcessor.postProcessAfterInitialization
 * 
 * 销毁：
 * 		单实例：容器关闭的时候
 * 		多实例：容器不会管理这个bean,容器不会调用销毁方法。
 *  
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一但返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 * 
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * initializeBean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *}
 *   
 *   
 * 1)、指定初始化和销毁方法
 * 	通过@Bean指定init-method和destory-method
 * 2)、通过让bean实现InitializingBean接口（定义初始化逻辑）
 * 				  DisposableBean接口（定义销毁逻辑）
 * @Component 
 * public class Cat implements InitializingBean, DisposableBean
 * 
 * 3)、可以使用JSR250
 * 		@PostConstruct 在bean创建完成并赋值好所有属性，来执行初始化方法。
 * 		@PreDestroy 在容器销毁bean前通知我们进行清理工作。
 * 
 * //在对象创建并赋值之后
	@PostConstruct
	public void init()
	
	//在容器移除对象之前
	@PreDestroy
	public void destory()
 * 
 * 4)、BeanPostProcessor:bean的后置处理器
 * 		在bean的初始化前后进行处理工作
 * 		postProcessBeforeInitialization：在初始化之前工作
 * 		postProcessAfterInitialization：在初始化之后工作
 * 
 * Spring底层对 BeanPostProcessor 的使用；
 * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor;
 * 
 * @author Dream
 *
 */
@Configuration
@ComponentScan("com.atguigu.bean")
public class MainConfigOfLifeCycle {

	//@Scope("prototype")
	@Bean(initMethod="init",destroyMethod="destory")
	public Car car() {
		return new Car();
	}
}

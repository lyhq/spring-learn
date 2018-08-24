package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.atguigu.bean.Color;
import com.atguigu.bean.Fruit;
import com.atguigu.bean.Person;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;

//类中组件统一设置，满足条件时，这个类的bean才能注册成功
@Conditional({WindowsCondition.class})
@Import({Color.class, Fruit.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) //快速的导入组件，id默认是全类名
@Configuration
public class MainConfig2 {
	
	//默认是单实例 singleton ioc容器启动的时候会调用方法创建person对象放到容器中，
	//以后就可以直接从容器中获取了
	//prototype: ioc容器启动的时候并不会创建person对象，而是在每次获取的时候才会去调用方法创建对象
	//懒加载：针对单实例bean,容器启动的时候创建bean;
	//		容器启动的时候先不创建对象，第一次使用的时候在创建对象，并进行初始化。
//	@Scope("prototype")
	@Lazy
	@Bean("person")
	public Person person() {
		return new Person("张三", 25);
	}
	
	/**
	 * @Conditional({Condition}) 按照一定的条件判断，满足条件则将bean注册入容器
	 */
	@Conditional({WindowsCondition.class})
	@Bean("Bill Gates")
	public Person person01(){
		return new Person("Bill Gates", 62);
	}
	
	@Conditional({LinuxCondition.class})
	@Bean("linus")
	public Person person02(){
		return new Person("Linus", 45);
	}

	/**
	 * 给容器中注册组件
	 * 1）包扫描+组件标注注解：（@Controller/@Service/@Repository/@Component）(自己写的)
	 * 2）@Bean: 导入的第三方包里面的组件
	 * 3）@Import: 快速的给容器中导入一个组件
	 * 		1、@Import(要导入到容器中的组件) 容器就会自动注册这个组件，id默认全类名
	 * 		2、ImportSelector： 返回需要导入的组件的全类名的数组
	 * 		3、ImportBeanDefinitionRegistrar
	 */
}

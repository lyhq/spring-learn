package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.atguigu.bean.Person;

//使用@PropertySource读取外部配置文件的k/v保存到运行的环境变量中；加载完外部的配置文件以后，使用${}取出文件的值。
@PropertySource(value= {"classpath:/person.properties"})
@Configuration//告诉Spring这是一个配置类
public class MainConfigOfPropertyValues {

	@Bean
	public Person person() {
		return new Person();
	}
}

package com.lyhq.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyhq on 18/08/22
 */
@RestController
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ImportResource({"classpath:/applicationContext.xml"})
public class Application {

    @Autowired
    private TestBiz testBiz;

    @RequestMapping("/")
    String home() throws Exception {
        System.out.println("controller 正常执行");
        try {
            testBiz.insertTest();
        } catch (Exception e) {
            System.out.println("controller 异常日志执行");//此处捕获到异常，说明整个事务已经回滚了
        }

        return " 正常返回Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}

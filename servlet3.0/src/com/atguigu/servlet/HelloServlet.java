package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")//此处代表处理请求为/hello的请求
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		System.out.println(Thread.currentThread().getName() + " start.........");
		try {
			String sayHello = sayHello("Tomcat...");
			resp.getWriter().write(sayHello);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end.........");
	}
	
	public String sayHello(String name) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " processing.........");
		Thread.sleep(3000);
		return "hello, " + name;
	}
}

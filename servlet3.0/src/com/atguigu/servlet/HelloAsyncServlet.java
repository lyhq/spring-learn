package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet3.0异步请求
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("主线程开始：" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
		// 支持异步处理asyncSupported = true
		// 开启异步模式
		AsyncContext startAsync = req.startAsync();

		// 业务逻辑进行异步处理，开始异步处理
		startAsync.start(new Runnable() {

			@Override
			public void run() {
				try {
					System.out
							.println("副线程开始：" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
					sayHello();
					startAsync.complete();// 异步调用完成
					// 获取异步的上下文
					AsyncContext asyncContext = req.getAsyncContext();
					// 获取响应
					ServletResponse response = asyncContext.getResponse();
					response.getWriter().write("Hello async...");
					System.out
							.println("副线程结束：" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		});
		System.out.println("主线程结束：" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
	}

	public void sayHello() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " processing.........");
		Thread.sleep(3000);
	}
}

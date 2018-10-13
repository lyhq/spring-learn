package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet3.0�첽����
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("���߳̿�ʼ��" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
		// ֧���첽����asyncSupported = true
		// �����첽ģʽ
		AsyncContext startAsync = req.startAsync();

		// ҵ���߼������첽������ʼ�첽����
		startAsync.start(new Runnable() {

			@Override
			public void run() {
				try {
					System.out
							.println("���߳̿�ʼ��" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
					sayHello();
					startAsync.complete();// �첽�������
					// ��ȡ�첽��������
					AsyncContext asyncContext = req.getAsyncContext();
					// ��ȡ��Ӧ
					ServletResponse response = asyncContext.getResponse();
					response.getWriter().write("Hello async...");
					System.out
							.println("���߳̽�����" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		});
		System.out.println("���߳̽�����" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
	}

	public void sayHello() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " processing.........");
		Thread.sleep(3000);
	}
}

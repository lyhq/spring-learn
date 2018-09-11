package com.atguigu.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * 
 * @Aspect： 告诉Spring当前类是一个切面类
 * @author Dream
 *
 */
@Aspect
public class LogAspects {

	//抽取共用的切入点表达式
	//1、本类引用
	//2、其他的切面引用
	@Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void pointCut() {}
	
	//1、本类使用
	//@Before在目标方法之前切入；切入点表达式，指定在哪个方法切入
	@Before("pointCut()")
	public void logStart(JoinPoint joinpoint) {
		Object[] args = joinpoint.getArgs();
		System.out.println(joinpoint.getSignature().getName() + "运行。。。@Before:参数列表是：{"+Arrays.asList(args)+"}");
	}
	
	//2、其他的切面使用（全类名.方法名）
	@After("com.atguigu.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinpoint) {
		System.out.println(joinpoint.getSignature().getName() + "结束。。。@After");
	}
	
	//注意：JoinPoint一定要出现在参数列表的第一位
	@AfterReturning(value="pointCut()", returning="result")
	public void logReturn(JoinPoint joinpoint, Object result) {
		System.out.println(joinpoint.getSignature().getName() + "正常返回。。。@AfterReturning:运行结果是：{"+result+"}");
	}
	
	@AfterThrowing(value="pointCut()", throwing="exception")
	public void logException(JoinPoint joinpoint, Exception exception) {
		System.out.println(joinpoint.getSignature().getName() + "异常。。。@AfterThrowing:异常信息是：{"+exception+"}");
	}
}

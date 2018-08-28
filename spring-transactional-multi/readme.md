spring嵌套事务案例，一个请求会调用执行到多个service方法。
Spring事务默认的回滚异常是RuntimeException

请求处理流程 contoroller-biz-service-dao(mapper)-数据库

biz作为外层，在controller和service中间，进行外层事务的管理，捕获service抛出的RuntimeException,
再将此RuntimeException抛给上层controller处理，此时外层biz的事务将会进行回滚，也就是整体事务的回滚。

service作为内层，进行内层事务的管理。处理数据操作的过程中发现异常，
只要将其捕获并抛出RuntimeException异常给上层也就是biz来处理就可以了，该service的内层事务将会回滚。

经过自己应用到工作中的AFC项目的交易文件的解析事务的控制 实践后，有如下总结：
spring的声明式事务是基于代理模式的. 其实代理模式相当简单, 就是将另一个类包裹在我们的类外面, 在调用我们创建的方法之前, 先经过外面的方法, 进行一些处理, 返回之前, 再进行一些操作。
private 方法无法添加事务管理.
final 方法无法添加事务管理.
static 方法无法添加事务管理.
当绕过代理对象, 直接调用添加事务管理的方法时, 事务管理将无法生效.

以下是在AFC（SC）项目中的实际配置：供参考
<!-- applicationContext.xml 配置切面 -->
<aop:config>
		<!--* com.sunway.sc.service..*.*(..) service方法必须在这个下面才会有事务的控制-->
        <aop:pointcut id="transactionPointcut" expression="execution(* com.sunway.sc.service..*.*(..))"/>
        <aop:advisor pointcut-ref="transactionPointcut" advice-ref="transactionAdvice"/>
</aop:config>

<!--代理对象的配置一定要加，因为事务本身就是通过代理来完成的-->
<aop:aspectj-autoproxy proxy-target-class="true"/>

<!-- 事务管理 属性 -->
<tx:advice id="transactionAdvice" transaction-manager="transactionManager">
    <tx:attributes>
    	<!--该配置要加，代表是一个事务的方法，REQUIRED是Spring默认的事务机制-->
        <tx:method name="parseTransFile" propagation="REQUIRED" rollback-for="Exception"/>
        <tx:method name="*" propagation="REQUIRED" read-only="true" rollback-for="Exception"/>
    </tx:attributes>
</tx:advice>

<!-- druid 连接池配置 -->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
      init-method="init" destroy-method="close">
      <!--取消事务的自动提交-->
    <property name="defaultAutoCommit" value="false"/>
</bean>

<!--如果使用了shiro, 且其中用到了相关的代理类，则需要加上<property name="proxyTargetClass" value="true"/>
	启用IOC容器中使用shiro的注解，但必须在配置 lifecycleBeanPostProcessor才可以使用-->
<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
      depends-on="lifecycleBeanPostProcessor">
      <!--加上该配置-->
    <property name="proxyTargetClass" value="true"/>
</bean>
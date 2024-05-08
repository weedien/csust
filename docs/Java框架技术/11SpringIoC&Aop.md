## 1. 任务要求

掌握Spring框架的基本原理和主要操作，完成如下任务：

（1）编写接口com.csust.Hello，声明方法sayHello(String name)；

（2）编写类User，实现接口com.csust.Hello，利用配置文件为User的字符串属性message注入“Hello”；

（3）调用User.sayHello（“tom”），输出Hello tom，实现IoC功能；

（4）编写LogBeforeAdvice，输出日志“Log before XXX method is called”，即编写日志逻辑，在方法被调用前输出日志；

（5）在配置文件中配置aop相关信息，要求在调用com.csust.Hello中的方法前切入日志逻辑（before）；

（6）思考：认真理解AOP的相关配置，写出模拟实现AOP功能的主要环节，仅写出实现AOP的主要原理和流程即可；

（7）将完成任务的主要思路、核心代码、主要配置、程序运行截图形成一个word文档提交。

## 2. 主要思路

AOP的核心思想是将横切关注点(如日志、事务管理等)
从业务逻辑中分离出来,使用代理模式在运行时动态地将横切关注点与业务逻辑进行织入。主要流程如下:

1. 定义切面(Aspect):包含横切逻辑的类,如`LogBeforeAdvice`。
2. 定义切入点(Pointcut):匹配连接点(方法执行位置)的表达式,如`execution(* com.csust.Hello.sayHello(..))`。
3. 定义通知(Advice):横切逻辑在切入点处执行的操作,如`@Before`注解标注的`logBefore()`方法。
4. 织入(Weaving):通过动态代理的方式,在运行时将通知织入到切入点处,实现横切逻辑与业务逻辑的结合。
5. Spring在运行时通过字节码生成动态创建代理对象,代理对象包含横切逻辑和目标对象。当调用目标对象的切入点方法时,代理对象先执行横切逻辑,再执行目标方法。

通过这种方式,AOP能够很好地实现关注点的分离,提高代码的可维护性和可重用性。

## 3. 核心代码

- Hello

```java
public interface Hello {
    String sayHello(String name);
}
```

- User

```java
public class User implements Hello {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String sayHello(String name) {
        return message + " " + name;
    }
}
```

- LogBeforeAdvice

```java
public class LogBeforeAdvice {

    public void logBefore() {
        log.info("Log before sayHello method is called");
    }
}
```

- 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="user" class="cn.weedien.csust.aop.User">
        <property name="message" value="Hello"/>
    </bean>

    <bean id="logBeforeAdvice" class="cn.weedien.csust.aop.LogBeforeAdvice"/>

    <aop:config>
        <aop:aspect id="aspectLogger" ref="logBeforeAdvice">
            <aop:before pointcut="execution(* cn.weedien.csust.aop.Hello.sayHello(..))" method="logBefore"/>
        </aop:aspect>
    </aop:config>

</beans>
```

## 4. 运行结果

- 将"Hello"注入User的message属性

![image-20240429143708418](C:\Users\weedien\AppData\Roaming\Typora\typora-user-images\image-20240429143708418.png)

- 在方法调用前输出日志

![image-20240429143643997](C:\Users\weedien\AppData\Roaming\Typora\typora-user-images\image-20240429143643997.png)
package cn.weedien.csust.aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect
public class LogBeforeAdvice {

    // @Before("execution(* cn.weedien.csust.aop.Hello.sayHello(..))")
    public void logBefore() {
        log.info("Log before sayHello method is called");
    }
}
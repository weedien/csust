package cn.weedien.csust.aop;

import cn.weedien.csust.advanced.aop.Hello;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class ApiTest {

    @Resource
    private ApplicationContext context;

    @Test
    public void test() {
        Hello hello = (Hello) context.getBean("user");
        log.info(hello.sayHello("Sunshine"));
    }
}

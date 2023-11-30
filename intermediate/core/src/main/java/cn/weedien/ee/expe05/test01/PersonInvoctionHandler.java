package cn.weedien.ee.expe05.test01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class PersonInvocationHandler implements InvocationHandler {
    private final Speakable target;

    public PersonInvocationHandler(cn.weedien.ee.expe05.test01.Speakable target2) {
        this.target = target2;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Class begin");
        Object result = method.invoke(target, args);
        System.out.println("Class end");
        return result;
    }
}
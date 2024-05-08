package cn.weedien.csust.medium.ee.expe05.test01;

import java.lang.reflect.Proxy;

class DynamicProxyExample {
    public static void main(String[] args) {
        Speakable target = new Teacher();
        Speakable proxy = (Speakable) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getSuperclass().getInterfaces(),
                new PersonInvocationHandler(target));

        proxy.speak("1、The java.lang and java.lang.reflect packages provide classes for java reflection.\n" +
                "2、Java Reflection is a process of examining or modifying the run time behavior of a class at run time.\n"
                +
                "3、The java.lang.Class class provides many methods that can be used to get metadata, examine and change the run time behavior of a class");
    }
}
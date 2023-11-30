package cn.weedien.ee.expe04.test03;

import cn.weedien.ee.expe04.test02.Person;
import cn.weedien.ee.expe04.test02.Teacher;

public class TestProxy {
    public static void main(String[] args) {
        Person person = new Teacher();
        PersonProxy personProxy = new PersonProxy(person);
        personProxy.speak("1、The java.lang and java.lang.reflect packages provide classes for java reflection.\n" +
                "2、Java Reflection is a process of examining or modifying the run time behavior of a class at run time.\n"
                +
                "3、The java.lang.Class class provides many methods that can be used to get metadata, examine and change the run time behavior of a class");
    }
}

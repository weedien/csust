package cn.weedien.csust.medium.ee.expe04.test02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {
    private static final String PACKAGE_NAME = Test.class.getPackage().getName() + ".";

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {

        // 0-通过反射创建Teacher的对象
        // 0-0 获取Teacher类的Class对象clazz
        Class<?> clazz = Class.forName(PACKAGE_NAME + "Teacher");

        // 0-1 获取clazz的Constructor对象constructor
        Constructor<?> constructor = clazz.getDeclaredConstructor();

        // 通过constructor对象创建Teacher对象t
        Teacher t = (Teacher) constructor.newInstance();

        // 1-通过反射执行t对象的Speak方法
        t.speak("Hello, Everyone!");

        // 2-通过反射设置t对象的属性position的属性值为”Master”
        Field positionField = clazz.getDeclaredField("positon");
        positionField.setAccessible(true);
        positionField.set(t, "Master");

        // 3-通过反射设置t对象私有属性salary的值为7000
        Field salaryField = clazz.getDeclaredField("salary");
        salaryField.setAccessible(true);
        salaryField.setInt(t, 7000);

        System.out.println(t.toString());
    }
}

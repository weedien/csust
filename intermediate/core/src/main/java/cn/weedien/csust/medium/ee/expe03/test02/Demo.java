package cn.weedien.csust.medium.ee.expe03.test02;

import java.lang.reflect.Field;

public class Demo {
    // 获取当前类的包名
    private static final String PACKAGE_NAME = Demo.class.getPackage().getName() + ".";

    // 将obj对象转化成为字符串的序列化方法
    public static String toString(Object obj) {
        StringBuilder sb = new StringBuilder();
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                String fieldName = fields[i].getName();
                Object fieldValue = fields[i].get(obj);
                sb.append(fieldName).append("=").append(fieldValue);
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return objClass.getSimpleName() + " [" + sb.toString() + "]";
    }

    // 将字符串转换为对象的反序列化方法
    public static Object fromString(String str) {
        int classNameEnd = str.indexOf("[");
        String className = str.substring(0, classNameEnd).trim();
        try {
            Class<?> objClass = Class.forName(PACKAGE_NAME + className);
            Object obj = objClass.getDeclaredConstructor().newInstance();

            String[] fieldPairs = str.substring(classNameEnd + 1, str.length() - 1).split(", ");
            for (String fieldPair : fieldPairs) {
                String[] parts = fieldPair.split("=");
                String fieldName = parts[0].trim();
                String fieldValue = parts[1].trim();
                Field field = objClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                if (field.getType() == int.class) {
                    field.set(obj, Integer.parseInt(fieldValue));
                } else {
                    field.set(obj, fieldValue);
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("Lucky", 18, "female");
        String str = Demo.toString(p1);
        System.out.println(str);
        Person p2 = (Person) Demo.fromString(str);
        System.out.println(p2);
    }
}
package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式一;

import java.util.Arrays;

public class MyMain {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                shapes[i] = new Circle(Math.random() * 10);
            } else if (i % 3 == 1) {
                shapes[i] = new Rectangle(Math.random() * 10, Math.random() * 10);
            } else {
                double[] sides = {Math.random() * 10, Math.random() * 10, Math.random() * 10};
                shapes[i] = new Polygon(sides);
            }
        }
        // 输出排序前的结果
        System.out.println("#################排序前的结果####################");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        Arrays.sort(shapes);

        // 输出排序后的结果
        System.out.println("#################排序后的结果####################");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }
}

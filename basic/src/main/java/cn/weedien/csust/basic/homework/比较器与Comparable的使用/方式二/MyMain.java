package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式二;

import java.util.Arrays;
import java.util.Comparator;

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

        // 定义一个比较器，用于比较两个形状的周长大小
        // Comparator<Shape> perimeterComparator = new Comparator<Shape>() {
        // @Override
        // public int compare(Shape o1, Shape o2) {
        // if (o1.getPerim() < o2.getPerim()) {
        // return -1;
        // } else if (o1.getPerim() > o2.getPerim()) {
        // return 1;
        // } else {
        // return 0;
        // }
        // }
        // };

        Comparator<Shape> perimeterComparator = (o1, o2) -> Double.compare(o1.getPerim(), o2.getPerim());

        // 输出排序前的结果
        System.out.println("#################排序前的结果####################");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        Arrays.sort(shapes, perimeterComparator);

        // 输出排序后的结果
        System.out.println("#################排序后的结果####################");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }
}

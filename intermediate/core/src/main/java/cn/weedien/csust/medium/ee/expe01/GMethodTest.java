package cn.weedien.csust.medium.ee.expe01;

import java.util.ArrayList;

/**
 * Circle需要重写compareTo来实现比较大小
 * 需要重写equals来实现去重
 */
class Circle implements Comparable<Circle> {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle radius: " + radius;
    }

    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.radius, other.radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }
}

public class GMethodTest {

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E item : list) {
            if (!newList.contains(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        if (list == null || list.length == 0) {
            throw new IllegalArgumentException("Input array is empty");
        }

        E maxElement = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i].compareTo(maxElement) > 0) {
                maxElement = list[i];
            }
        }
        return maxElement;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Circle> circles = new ArrayList<>();

        list.add(14);
        list.add(24);
        list.add(14);
        list.add(12);

        circles.add(new Circle(3));
        circles.add(new Circle(2.9));
        circles.add(new Circle(5.9));
        circles.add(new Circle(3));

        ArrayList<Integer> newList = removeDuplicates(list);
        ArrayList<Circle> newCircles = removeDuplicates(circles);

        System.out.println(newList); // 输出 [14, 24, 12]
        System.out.println(newCircles); // 输出 [Circle radius: 3.0, Circle radius: 2.9, Circle radius: 5.9]

        Integer[] numbers = {1, 2, 3};
        System.out.println(max(numbers)); // 输出 3

        Circle[] c = {new Circle(3), new Circle(2.9), new Circle(5.9)};
        System.out.println(max(c)); // 输出 Circle radius: 5.9
    }
}

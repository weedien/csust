package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式二;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerim() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "圆形 周长为：" + String.format("%.2f", this.getPerim());
    }
}
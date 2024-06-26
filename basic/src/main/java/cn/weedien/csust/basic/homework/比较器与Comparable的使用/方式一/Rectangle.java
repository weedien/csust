package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式一;

public class Rectangle extends Geograph {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getPerim() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "长方形 周长为：" + String.format("%.2f", this.getPerim());
    }

}
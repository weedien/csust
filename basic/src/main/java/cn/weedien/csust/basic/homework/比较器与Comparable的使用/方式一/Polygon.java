package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式一;

public class Polygon extends Geograph {
    private double[] sides;

    public Polygon(double[] sides) {
        this.sides = sides;
    }

    @Override
    public double getPerim() {
        double sum = 0;
        for (double side : sides) {
            sum += side;
        }
        return sum;
    }

    @Override
    public String toString() {
        return "多边形 周长为：" + String.format("%.2f", this.getPerim());
    }
}
package cn.weedien;

import java.util.Scanner;

public class Circle {
    private double redius;
    private double area;

    public double getRedius() {
        return redius;
    }

    public void setRedius(double redius) {
        this.redius = redius;
    }

    public String getArea() {
        this.area = this.redius * redius * Math.PI;

        return String.format("%.2f", area).toString();
    }

    @Override
    public String toString() {
        return "圆的半径为：" + this.redius + "，面积为：" + this.getArea();
    }
}

class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Circle circle = new Circle();

        System.out.println("请输入圆的半径：");
        circle.setRedius(sc.nextDouble());
        sc.close();

        System.out.println(circle);
    }
}
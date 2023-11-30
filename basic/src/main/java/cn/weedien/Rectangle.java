package cn.weedien;

import java.util.Scanner;

public class Rectangle {
    private float width;
    private float length;

    private float area;

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getArea() {
        this.area = this.length * this.width;
        return this.area;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Rectangle rect = new Rectangle();

        System.out.println("请输入长方形的长：");
        rect.setLength(sc.nextFloat());
        System.out.println("请输入长方形的宽：");
        rect.setWidth(sc.nextFloat());
        float area = rect.getArea();

        System.out.println("长方形的面积为：" + area);

        sc.close();
    }
}
package cn.weedien.csust.basic.homework.凸多边形面积;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入凸多边形的顶点数：");
        int n = scanner.nextInt();

        double[] xCoords = new double[n];
        double[] yCoords = new double[n];
        System.out.print("请输入凸多边形的顶点坐标：");
        for (int i = 0; i < n; i++) {
            xCoords[i] = scanner.nextDouble();
            yCoords[i] = scanner.nextDouble();
        }

        scanner.close();
        ConvexPolygon polygon = new ConvexPolygon(xCoords, yCoords);
        System.out.println("凸多边形面积：" + polygon.getArea());
    }
}

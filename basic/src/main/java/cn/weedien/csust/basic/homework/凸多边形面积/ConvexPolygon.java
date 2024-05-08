package cn.weedien.csust.basic.homework.凸多边形面积;

public class ConvexPolygon {
    protected double[] xCoords;
    protected double[] yCoords;

    public ConvexPolygon(double[] xCoords, double[] yCoords) {
        this.xCoords = xCoords;
        this.yCoords = yCoords;
    }

    public double getArea() {
        double area = 0;
        int n = xCoords.length;
        for (int i = 0; i < n; i++) {
            area += xCoords[i] * yCoords[(i + 1) % n] - yCoords[i] * xCoords[(i + 1) % n];
        }
        return 0.5 * Math.abs(area);
    }
}

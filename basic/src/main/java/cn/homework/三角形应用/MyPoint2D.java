package cn.homework.三角形应用;

public class MyPoint2D {
    private double x;
    private double y;

    public MyPoint2D() {
        x = 0;
        y = 0;
    }

    public MyPoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

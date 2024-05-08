package cn.weedien.csust.basic.homework.三角形异常;

public class Triangle {
    private double side1;
    private double side2;
    private double side3;

    public Triangle() {
        this.side1 = 0;
        this.side2 = 0;
        this.side3 = 0;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public void setTriangle(double a1, double a2, double a3) throws InvalidTriangleException {
        if (a1 + a2 <= a3 || a1 + a3 <= a2 || a2 + a3 <= a1) {
            throw new InvalidTriangleException(a1, a2, a3);
        }
        this.side1 = a1;
        this.side2 = a2;
        this.side3 = a3;
    }
}

package cn.weedien.csust.basic.homework.三角形应用;

import java.awt.geom.Line2D;

public class Triangle2D {
    private MyPoint2D p1;
    private MyPoint2D p2;
    private MyPoint2D p3;

    public Triangle2D() {
        this.p1 = new MyPoint2D(0, 0);
        this.p2 = new MyPoint2D(1, 1);
        this.p3 = new MyPoint2D(2, 5);
    }

    public Triangle2D(MyPoint2D p1, MyPoint2D p2, MyPoint2D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    private static double Multiply(MyPoint2D p1, MyPoint2D p2, MyPoint2D p0) {
        return (p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) - (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
    }

    public MyPoint2D getP1() {
        return p1;
    }

    public void setP1(MyPoint2D p1) {
        this.p1 = p1;
    }

    public MyPoint2D getP2() {
        return p2;
    }

    public void setP2(MyPoint2D p2) {
        this.p2 = p2;
    }

    public MyPoint2D getP3() {
        return p3;
    }

    public void setP3(MyPoint2D p3) {
        this.p3 = p3;
    }

    public double getArea() {
        double a = p1.getX() * (p2.getY() - p3.getY());
        double b = p2.getX() * (p3.getY() - p1.getY());
        double c = p3.getX() * (p1.getY() - p2.getY());
        return Math.abs((a + b + c) / 2);
    }

    public double getPerimeter() {
        Line2D line1 = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        Line2D line2 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        Line2D line3 = new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY());
        return line1.getP1().distance(line1.getP2()) + line2.getP1().distance(line2.getP2())
                + line3.getP1().distance(line3.getP2());
    }

    public boolean contains(MyPoint2D p) {
        double p1p2p = Multiply(p1, p2, p);
        double p2p3p = Multiply(p2, p3, p);
        double p3p1p = Multiply(p3, p1, p);
        double p1p2t = Multiply(p1, p2, p3);
        double p2p3t = Multiply(p2, p3, p1);
        double p3p1t = Multiply(p3, p1, p2);
        return p1p2p * p1p2t > 0 && p2p3p * p2p3t > 0 && p3p1p * p3p1t > 0;
    }

    public boolean contains(Triangle2D t) {
        return contains(t.getP1()) && contains(t.getP2()) && contains(t.getP3());
    }

    public boolean overlaps(Triangle2D t) {
        Line2D line1 = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        Line2D line2 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        Line2D line3 = new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY());
        Line2D tLine1 = new Line2D.Double(t.getP1().getX(), t.getP1().getY(), t.getP2().getX(), t.getP2().getY());
        Line2D tLine2 = new Line2D.Double(t.getP2().getX(), t.getP2().getY(), t.getP3().getX(), t.getP3().getY());
        Line2D tLine3 = new Line2D.Double(t.getP3().getX(), t.getP3().getY(), t.getP1().getX(), t.getP1().getY());

        // 检查两个三角形的边是否相交
        if (line1.intersectsLine(tLine1) || line1.intersectsLine(tLine2) || line1.intersectsLine(tLine3) ||
                line2.intersectsLine(tLine1) || line2.intersectsLine(tLine2) || line2.intersectsLine(tLine3) ||
                line3.intersectsLine(tLine1) || line3.intersectsLine(tLine2) || line3.intersectsLine(tLine3)) {
            return true;
        }

        // 检查一个三角形的任意一个顶点是否位于另一个三角形内部
        if (contains(t.getP1()) || contains(t.getP2()) || contains(t.getP3()) ||
                t.contains(p1) || t.contains(p2) || t.contains(p3)) {
            return true;
        }

        return false;
    }
}
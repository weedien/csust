package cn.homework.一元二次方程;

class Complex {
    private double real;
    private double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }
}

class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Complex getRoot1() {
        double discriminant = getDiscriminant();
        if (discriminant < 0) {
            return null;
        } else {
            double root1_real = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root1_imag = 0.0;
            return new Complex(root1_real, root1_imag);
        }
    }

    public Complex getRoot2() {
        double discriminant = getDiscriminant();
        if (discriminant < 0) {
            return null;
        } else {
            double root2_real = (-b - Math.sqrt(discriminant)) / (2 * a);
            double root2_imag = 0.0;
            return new Complex(root2_real, root2_imag);
        }
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    // 重写toString，系数为1时不显示1
    @Override
    public String toString() {
        String str = "";
        if (a == 1) {
            str += "x^2";
        } else {
            str += a + "x^2";
        }
        if (b == 1) {
            str += " + x";
        } else if (b == -1) {
            str += " - x";
        } else if (b > 0) {
            str += " + " + b + "x";
        } else if (b < 0) {
            str += " - " + (-b) + "x";
        }
        if (c > 0) {
            str += " + " + c;
        } else if (c < 0) {
            str += " - " + (-c);
        }
        return str + " = 0";
    }
}

public class TestQuadraticEquation {
    public static void main(String[] args) {
        QuadraticEquation eq = new QuadraticEquation(1, -5, 6);
        Complex root1 = eq.getRoot1();
        Complex root2 = eq.getRoot2();
        System.out.println("输入方程为：" + eq);
        if (root1 != null) {
            System.out.println("Root1: " + root1);
        }
        if (root2 != null) {
            System.out.println("Root2: " + root2);
        }
    }
}
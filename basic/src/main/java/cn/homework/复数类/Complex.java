package cn.homework.复数类;

class Complex {

    private double a;
    private double b;

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getRealPart() {
        return a;
    }

    public void setRealPart(double a) {
        this.a = a;
    }

    public double getImaginaryPart() {
        return b;
    }

    public void setImaginaryPart(double b) {
        this.b = b;
    }

    // 加法运算
    public Complex add(Complex other) {
        double real = this.a + other.a;
        double imaginary = this.b + other.b;
        return new Complex(real, imaginary);
    }

    // 减法运算
    public Complex subtract(Complex other) {
        double real = this.a - other.a;
        double imaginary = this.b - other.b;
        return new Complex(real, imaginary);
    }

    // 乘法运算
    public Complex multiply(Complex other) {
        double real = (this.a * other.a) - (this.b * other.b);
        double imaginary = (this.a * other.b) + (this.b * other.a);
        return new Complex(real, imaginary);
    }

    // 除法运算
    public Complex divide(Complex other) {
        double denominator = (other.a * other.a) + (other.b * other.b);
        double real = ((this.a * other.a) + (this.b * other.b)) / denominator;
        double imaginary = ((this.b * other.a) - (this.a * other.b))
                / denominator;
        return new Complex(real, imaginary);
    }

    @Override
    public String toString() {
        if (b == 0) {
            return String.format("%.2f", a);
        } else if (a == 0) {
            return String.format("%.2fi", b);
        } else if (b < 0) {
            return String.format("%.2f - %.2fi", a, Math.abs(b));
        } else {
            return String.format("%.2f + %.2fi", a, b);
        }
    }

    public static void main(String[] args) {

        Complex c1 = new Complex(4, 9);

        Complex c2 = new Complex(5, -1);

        // 输出c1
        System.out.println("c1 = " + c1);
        // 输出c2
        System.out.println("c2 = " + c2);
        // 加法运算
        System.out.println("c1 + c2 = " + c1.add(c2));
        // 减法运算
        System.out.println("c1 - c2 = " + c1.subtract(c2));
        // 乘法运算
        System.out.println("c1 * c2 = " + c1.multiply(c2));
        // 除法运算
        System.out.println("c1 / c2 = " + c1.divide(c2));

    }
}

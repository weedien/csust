package cn.weedien.csust.basic.homework.图形2;

class Triangle extends GeoGraph {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c, int color, boolean filled) {
        super(color, filled);
        this.a = a;
        this.b = b;
        this.c = c;
        procColor();
    }

    public double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public void procColor() {
        int colorValue = (int) (getArea() / 100);
        if (colorValue < 10) {
            color = colorValue;
        } else {
            color = Integer.parseInt(String.valueOf(colorValue).substring(0, 2));
        }
    }

    public String toString() {
        return "Triangle: " + getArea();
    }
}
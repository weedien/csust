package cn.homework.图形2;

class Oval extends GeoGraph {
    private double a;
    private double b;

    public Oval(double a, double b, int color, boolean filled) {
        super(color, filled);
        this.a = a;
        this.b = b;
        procColor();
    }

    public double getArea() {
        return Math.PI * a * b;
    }

    public double getPerimeter() {
        double h = Math.pow(a - b, 2) / Math.pow(a + b, 2);
        return Math.PI * (a + b) * (1 + 3 * h / (10 + Math.sqrt(4 - 3 * h)));
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
        return "Oval: " + getArea();
    }
}
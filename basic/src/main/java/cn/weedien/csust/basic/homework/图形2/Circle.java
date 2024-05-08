package cn.weedien.csust.basic.homework.图形2;

class Circle extends GeoGraph {
    private final double radius;

    public Circle(double radius, int color, boolean filled) {
        super(color, filled);
        this.radius = radius;
        procColor();
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
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
        return "Circle: " + getArea();
    }
}

package cn.homework.图形2;

class SemiCircle extends GeoGraph {
    private double radius;

    public SemiCircle(double radius, int color, boolean filled) {
        super(color, filled);
        this.radius = radius;
        procColor();
    }

    public double getArea() {
        return Math.PI * radius * radius / 2;
    }

    public double getPerimeter() {
        return Math.PI * radius + 2 * radius;
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
        return "SemiCircle: " + getArea();
    }
}
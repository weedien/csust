package cn.homework.图形2;

class Rectangle extends GeoGraph {
    private double width;
    private double height;

    public Rectangle(double width, double height, int color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
        procColor();
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
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
        return "Rectangle: " + getArea();
    }
}
package cn.weedien.csust.basic.homework.图形2;

class Trapezoid extends GeoGraph {
    private final double top;
    private final double bottom;
    private final double height;
    private final double left;
    private final double right;

    public Trapezoid(double top, double bottom, double height, double left, double right, int color, boolean filled) {
        super(color, filled);
        this.top = top;
        this.bottom = bottom;
        this.height = height;
        this.left = left;
        this.right = right;
        procColor();
    }

    public double getArea() {
        return (top + bottom) * height / 2;
    }

    public double getPerimeter() {
        return top + bottom + left + right;
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
        return "Trapezoid: " + getArea();
    }
}

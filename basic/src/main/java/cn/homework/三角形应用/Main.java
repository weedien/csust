package cn.homework.三角形应用;

public class Main {
    public static void main(String[] args) {
        Triangle2D triangle1 = new Triangle2D(); // Create default triangle
        System.out.println("Triangle 1:");
        System.out.println("Area: " + triangle1.getArea());
        System.out.println("Perimeter: " + triangle1.getPerimeter());
        System.out.println("Contains point (1, 1): " + triangle1.contains(new MyPoint2D(1, 1)));

        Triangle2D triangle2 = new Triangle2D(new MyPoint2D(1, 1), new MyPoint2D(4, 5), new MyPoint2D(2, 8));
        System.out.println("\nTriangle 2:");
        System.out.println("Area: " + triangle2.getArea());
        System.out.println("Perimeter: " + triangle2.getPerimeter());
        System.out.println("Contains point (3, 4): " + triangle2.contains(new MyPoint2D(3, 4)));

        System.out.println("Triangle 1 Contains triangle 2: " + triangle1.contains(triangle2));
        System.out.println("Triangle 1 Overlaps with triangle 2: " + triangle1.overlaps(triangle2));

        Triangle2D triangle3 = new Triangle2D(new MyPoint2D(2, 2), new MyPoint2D(3, 3), new MyPoint2D(4, 4));
        System.out.println("\nTriangle 3:");
        System.out.println("Area: " + triangle3.getArea());
        System.out.println("Perimeter: " + triangle3.getPerimeter());
    }
}

package cn.weedien.csust.basic.homework.三角形异常;

public class Main {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle();
        try {
            triangle1.setTriangle(3, 4, 5);
            System.out.println(
                    "Triangle sides: " + triangle1.getSide1() + ", " + triangle1.getSide2() + ", "
                            + triangle1.getSide3());
        } catch (InvalidTriangleException e) {
            System.out.println(e.toString());
        }

        Triangle triangle2 = new Triangle();
        try {
            triangle2.setTriangle(1, 1, 3);
            System.out.println("Triangle sides: " + triangle2.getSide1() + ", " + triangle2.getSide2() + ", "
                    + triangle2.getSide3());
        } catch (InvalidTriangleException e) {
            System.out.println(e.toString());
        }
    }
}

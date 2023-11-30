package cn.homework.三角形异常;

public class InvalidTriangleException extends Exception {
    public InvalidTriangleException(double a1, double a2, double a3) {
        super("Invalid triangle sides: " + a1 + ", " + a2 + ", " + a3);
    }

    @Override
    public String toString() {
        return "InvalidTriangleException: " + super.getMessage() + ". They cannot form a triangle.";
    }
}

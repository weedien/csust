package cn.weedien.ee.expe04.test02;

public class Teacher extends Person {
    public Teacher() {
    }

    public String positon;
    private int salary;

    public void speak(String message) {
        System.out.println("Speak: " + message);
    }

    @Override
    public String toString() {
        return "[Positon=" + positon + " salary= " + salary +
                "]";
    }

    public int getSalary() {
        return salary;
    }
}

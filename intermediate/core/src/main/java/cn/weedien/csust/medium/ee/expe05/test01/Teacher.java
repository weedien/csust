package cn.weedien.csust.medium.ee.expe05.test01;

public class Teacher extends Person {
    public String positon;
    private int salary;

    public Teacher() {
    }

    @Override
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

package cn.weedien.csust.advanced.T1_reflectio_and_dynamic_proxy;


public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "John", 20);
        IStudent studentProxy = (IStudent) StudentProxy.createProxy(new StudentImpl());
        studentProxy.doExercise("Math");
    }
}

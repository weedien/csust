package cn.weedien.ee.expe04.test01;

class Sub implements Calculator {
    @Override
    public double calculate(double num1, double num2) {
        return num1 - num2;
    }
}

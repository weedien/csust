package cn.weedien.designpattern.prototype;

public class Car extends Prototype<Car> {
    @Override
    public String toString() {
        return "我是一辆小汽车！";
    }
}

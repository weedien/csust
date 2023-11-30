package cn.weedien.designpattern.prototype;

public class App {
    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactoryImpl(
                new Bus(),
                new Car(),
                new Truck());
        Bus bus = factory.getBus();
        Car car = factory.getCar();
        Truck truck = factory.getTruck();
        System.out.println(bus);
        System.out.println(car);
        System.out.println(truck);
    }
}

package cn.weedien.csust.medium.designpattern.prototype;

public class VehicleFactoryImpl implements VehicleFactory {

    private final Bus bus;
    private final Car car;
    private final Truck truck;

    public VehicleFactoryImpl(Bus bus, Car car, Truck truck) {
        this.bus = bus;
        this.car = car;
        this.truck = truck;
    }

    @Override
    public Bus getBus() {
        return bus.copy();
    }

    @Override
    public Car getCar() {
        return car.copy();
    }

    @Override
    public Truck getTruck() {
        return truck.copy();
    }
}
package cn.weedien.designpattern.prototype;

public interface VehicleFactory {

    Bus getBus();

    Car getCar();

    Truck getTruck();
}
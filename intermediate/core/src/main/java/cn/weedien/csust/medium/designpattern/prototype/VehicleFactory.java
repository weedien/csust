package cn.weedien.csust.medium.designpattern.prototype;

public interface VehicleFactory {

    Bus getBus();

    Car getCar();

    Truck getTruck();
}
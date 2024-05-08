package cn.weedien.csust.medium.ee.expe10.test02;

public class Main {
    public static void main(String[] args) {
        RideShare rideShare = new RideShare();

        // 创建乘客线程
        Thread passenger1 = new Thread(new Passenger("乘客A", rideShare));
        Thread passenger2 = new Thread(new Passenger("乘客B", rideShare));
        Thread passenger3 = new Thread(new Passenger("乘客C", rideShare));

        // 启动乘客线程
        passenger1.start();
        passenger2.start();
        passenger3.start();
    }
}

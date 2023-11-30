package cn.weedien.ee.expe10.test01;

public class Main {
    public static void main(String[] args) {
        FruitPlate fruitPlate = new FruitPlate();

        // 创建服务员和客人线程
        Thread servantThread = new Thread(new Servant(fruitPlate));
        Thread guestThread = new Thread(new Guest(fruitPlate));

        // 启动服务员和客人线程
        servantThread.start();
        guestThread.start();

    }
}
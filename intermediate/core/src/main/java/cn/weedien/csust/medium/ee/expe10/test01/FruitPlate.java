package cn.weedien.csust.medium.ee.expe10.test01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FruitPlate {
    private BlockingQueue<String> plate = new ArrayBlockingQueue<>(5);

    // 服务员放桃子到果盘
    public void putPeach() throws InterruptedException {
        plate.put("Peach");
        System.out.println("服务员放入一个桃子。当前数量为：" + plate.size());
    }

    // 客人从果盘取桃子
    public void takePeach() throws InterruptedException {
        plate.take();
        System.out.println("客人取走一个桃子，当前数量为：" + plate.size());
    }

    public int size() {
        return plate.size();
    }
}

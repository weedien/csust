package cn.weedien.csust.medium.ee.expe10.test02;

import java.util.concurrent.CountDownLatch;

class RideShare {
    private static final int PASSENGER_COUNT = 3;
    private CountDownLatch countDownLatch;

    public RideShare() {
        this.countDownLatch = new CountDownLatch(PASSENGER_COUNT);
    }

    // 乘客加入拼单
    public void joinRide(String passengerName) throws InterruptedException {
        System.out.println(passengerName + " 加入了拼车");
        countDownLatch.countDown(); // 减少计数
        countDownLatch.await(); // 等待计数归零
        System.out.println("准备就绪！出发...");
    }
}
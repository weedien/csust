package cn.weedien.ee.expe09.test03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PeachParty {
    private static final int MAX_PEACHES = 5;
    private int peachCount = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition guestCondition = lock.newCondition();
    private final Condition servantCondition = lock.newCondition();

    public void guestTakePeach() {
        lock.lock();
        try {
            // 如果果盘为空，则等待服务员装满桃子
            while (peachCount == 0) {
                try {
                    // 通知服务员果盘为空
                    servantCondition.signal();
                    guestCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从果盘中取出一个桃子
            peachCount--;
            System.out.println(Thread.currentThread().getName() + " 取走了一个桃子");
        } finally {
            lock.unlock();
        }
    }

    public void servantRefillPeaches() {
        lock.lock();
        try {
            // 如果果盘未满，则等待客人取走桃子
            while (peachCount > 0) {
                try {
                    servantCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 装满果盘
            peachCount = MAX_PEACHES;
            System.out.println("服务员装满了 " + peachCount + " 个桃子");
            // 通知客人可以继续取桃子
            guestCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PeachParty peachParty = new PeachParty();

        Thread guest1 = new Thread(() -> {
            while (true) {
                peachParty.guestTakePeach();
                try {
                    Thread.sleep(1000); // 模拟客人吃桃子的时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread guest2 = new Thread(() -> {
            while (true) {
                peachParty.guestTakePeach();
                try {
                    Thread.sleep(1000); // 模拟客人吃桃子的时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread servant = new Thread(() -> {
            while (true) {
                peachParty.servantRefillPeaches();
                try {
                    Thread.sleep(2000); // 模拟服务员装满桃子的时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        guest1.start();
        guest2.start();
        servant.start();
    }
}
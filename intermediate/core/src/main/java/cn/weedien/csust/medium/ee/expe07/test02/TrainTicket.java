package cn.weedien.csust.medium.ee.expe07.test02;

import java.util.concurrent.locks.ReentrantLock;

class TrainTicket {
    private int availableSeats;
    private ReentrantLock lock = new ReentrantLock();

    public TrainTicket(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // 售票方法
    public void sellTicket() {
        lock.lock(); // 获取锁
        try {
            if (availableSeats > 0) {
                System.out.println(Thread.currentThread().getName() + " 售出第 " + availableSeats + " 张票");
                availableSeats--;
                Thread.sleep(200); // 模拟售票过程，线程休眠200毫秒
            } else {
                System.out.println(Thread.currentThread().getName() + " 票已售罄");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

}

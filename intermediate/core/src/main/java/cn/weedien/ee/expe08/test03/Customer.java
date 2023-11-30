package cn.weedien.ee.expe08.test03;

import java.util.concurrent.Semaphore;

public class Customer extends Thread {
    private static Seller seller;
    private static Semaphore semaphore;

    public Customer(Seller seller, Semaphore semaphore) {
        Customer.seller = seller;
        Customer.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            if (seller.makePurchase()) {
                System.out.println(Thread.currentThread().getName() + " successfully purchased the item.");
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to purchase the item.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

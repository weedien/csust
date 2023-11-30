package cn.weedien.ee.expe08.test03;

import java.util.concurrent.Semaphore;

public class DoubleElevenSeckill {
    public static void main(String[] args) {
        int availableStock = 3;
        int numCustomers = 5;

        Seller seller = new Seller(availableStock);
        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < numCustomers; i++) {
            Thread customerThread = new Customer(seller, semaphore);
            customerThread.start();
        }
    }
}

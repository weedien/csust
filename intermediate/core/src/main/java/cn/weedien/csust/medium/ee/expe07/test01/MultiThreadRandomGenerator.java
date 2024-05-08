package cn.weedien.csust.medium.ee.expe07.test01;

import java.util.Random;

public class MultiThreadRandomGenerator {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int totalNumbers = 1000000;
        int numThreads = 4;
        int numbersPerThread = totalNumbers / numThreads;

        class RandomGeneratorThread extends Thread {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < numbersPerThread; i++) {
                    random.nextInt(); // 生成随机数
                }
            }
        }

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new RandomGeneratorThread();
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("多线程生成 " + totalNumbers + " 个随机数用时：" + totalTime + " 毫秒");
    }
}

package cn.weedien.csust.medium.ee.expe06.test01;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadPrimeNumberApp {
    public static void main(String[] args) {
        MultiThreadPrimeNumberApp primeNumberApp = new MultiThreadPrimeNumberApp();
        primeNumberApp.execute();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        long number = scanner.nextLong();
        System.out.println("输入了：" + number);

        while (number != 0) {
            // 统计时间
            long startTime = System.currentTimeMillis();
            countPrimeNumberConcurrently(number);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：" + (endTime - startTime) + "ms");

            System.out.println("请输入：");
            number = scanner.nextLong();
            System.out.println("输入了：" + number);
        }
        scanner.close();
    }

    /**
     * 使用多个线程并发计算，为每个线程分配一部分计算任务，最后汇总结果
     *
     * @param number 用户输入的数字
     */
    public void countPrimeNumberConcurrently(long number) {
        int numThreads = Runtime.getRuntime().availableProcessors(); // 获取可用处理器核心数
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        AtomicLong primeCount = new AtomicLong(0);

        long batchSize = number / numThreads;
        long start = 2;

        for (int i = 0; i < numThreads; i++) {
            long end = (i == numThreads - 1) ? number : start + batchSize - 1;
            Runnable task = new PrimeNumberCounter(start, end, primeCount);
            executor.execute(task);
            start = end + 1;
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // 等待所有线程结束
        }

        System.out.println("小于:" + number + " 的质数个数：" + primeCount.get());
    }

    public class PrimeNumberCounter implements Runnable {
        private long start;
        private long end;
        private AtomicLong primeCount;

        public PrimeNumberCounter(long start, long end, AtomicLong primeCount) {
            this.start = start;
            this.end = end;
            this.primeCount = primeCount;
        }

        @Override
        public void run() {
            long count = 0;
            for (long i = start; i <= end; i++) {
                if (isPrimeNumber(i)) {
                    count++;
                }
            }
            primeCount.addAndGet(count);
        }

        public boolean isPrimeNumber(long number) {
            if (number <= 1) {
                return false;
            }

            long sqrNumber = (long) Math.sqrt(number);
            for (long i = 2; i <= sqrNumber; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}

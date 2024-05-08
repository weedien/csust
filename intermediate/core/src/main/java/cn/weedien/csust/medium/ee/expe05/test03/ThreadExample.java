package cn.weedien.csust.medium.ee.expe05.test03;

import java.util.concurrent.*;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建第一个线程任务，并使用Callable接口实现
        Callable<Integer> sumTask = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };

        // 创建第二个线程任务，打印字母a 100次
        Runnable printATask = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("a ");
            }
        };

        // 创建第三个线程任务，打印字母b 100次
        Runnable printBTask = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("b ");
            }
        };

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 启动第一个线程任务并获取计算结果
        Future<Integer> sumResult = executor.submit(sumTask);

        // 启动第二个线程任务
        executor.execute(printATask);

        // 启动第三个线程任务
        executor.execute(printBTask);

        // 等待第一个线程任务完成并获取结果
        int sum = sumResult.get();

        // 关闭线程池
        executor.shutdown();

        // 输出第一个线程计算的和的结果
        System.out.println("Sum: " + sum);
    }
}
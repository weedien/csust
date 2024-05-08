package cn.weedien.csust.medium.ee.expe08.test01;

public class SynchronizationInheritanceTest {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_THREADS = 5;

        // 创建父类对象并启动多个线程
        Parent parent = new Parent();
        Thread[] parentThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            parentThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    parent.increment();
                }
            });
            parentThreads[i].start();
        }

        // 等待父类线程执行完毕
        for (int i = 0; i < NUM_THREADS; i++) {
            parentThreads[i].join();
        }

        System.out.println("Counter value in Parent class: " + parent.counter);

        // 创建子类对象并启动多个线程
        Child child = new Child();
        Thread[] childThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            childThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    child.increment();
                }
            });
            childThreads[i].start();
        }

        // 等待子类线程执行完毕
        for (int i = 0; i < NUM_THREADS; i++) {
            childThreads[i].join();
        }

        System.out.println("Counter value in Child class: " + child.counter);
    }
}
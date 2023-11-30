package cn.weedien.ee.expe09.test04;

import java.util.LinkedList;

class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
    }

    public synchronized void put(String product) throws InterruptedException {
        while (buffer.size() == maxSize) {
            wait(); // 等待缓冲区有空位置
        }

        buffer.add(product);
        System.out.println("Producer put: " + product);

        notifyAll(); // 通知等待的线程可以进行操作
    }

    public synchronized String get() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // 等待缓冲区有产品
        }

        String product = buffer.removeFirst();
        System.out.println("Consumer get: " + product);

        notifyAll(); // 通知等待的线程可以进行操作

        return product;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String product = "产品" + i;
                buffer.put(product);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(1); // 缓冲区大小为1

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

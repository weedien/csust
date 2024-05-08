package cn.weedien.csust.basic.demo.抢红包;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedPacket {

    private double totalAmount; // 红包总金额
    private int count; // 红包个数
    private double[] amounts; // 每个红包的金额
    private volatile int index; // 当前红包索引，使用 volatile 保证可见性
    private Map<String, Double> claimedAmounts; // 记录每个用户已经抢到的金额

    public RedPacket(double totalAmount, int count) {
        this.totalAmount = totalAmount;
        this.count = count;
        this.amounts = new double[count];
        this.claimedAmounts = new ConcurrentHashMap<>();
        this.index = 0;
        init();
    }

    public static String getLuckyGuy(List<Thread> threads, RedPacket redPacket) {
        double maxAmount = Double.MIN_VALUE;
        String luckyGuy = "";
        for (Thread thread : threads) {
            String name = thread.getName();
            try {
                thread.join(); // 等待线程执行完毕
                double amount = redPacket.getClaimedAmount(name);
                if (amount > maxAmount) {
                    maxAmount = amount;
                    luckyGuy = name;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return luckyGuy;
    }

    public static String getUnluckyGuy(List<Thread> threads, RedPacket redPacket) {
        double minAmount = Double.MAX_VALUE;
        String unluckyGuy = "";
        for (Thread thread : threads) {
            String name = thread.getName();
            try {
                thread.join(); // 等待线程执行完毕
                double amount = redPacket.getClaimedAmount(name);
                if (amount < minAmount) {
                    minAmount = amount;
                    unluckyGuy = name;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return unluckyGuy;
    }

    public static void main(String[] args) {
        int threadCount = 10;
        double totalAmount = 100;
        int count = 10;

        RedPacket redPacket = new RedPacket(totalAmount, count);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(() -> {
                try {
                    double amount = redPacket.getAmount();
                    System.out.println(Thread.currentThread().getName() + ": 抢到了 " + amount + " 元");
                } catch (RedPacketException e) {
                    System.out.println(Thread.currentThread().getName() + ": " + e.getMessage());
                }
            }, "用户" + (i + 1)));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String luckyGuy = RedPacket.getLuckyGuy(threads, redPacket);
        System.out.println("运气王是：" + luckyGuy);

        String unluckyGuy = RedPacket.getUnluckyGuy(threads, redPacket);
        System.out.println("运气最差的人是：" + unluckyGuy);
    }

    private void init() {
        // 计算每个红包的金额，保留两位小数
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(Math.random());
        }
        double sum = list.stream().mapToDouble(Double::doubleValue).sum();
        for (int i = 0; i < count; i++) {
            amounts[i] = Math.floor((list.get(i) / sum) * totalAmount * 100) / 100;
        }
        // 打乱红包顺序
        List<Double> amountList = new ArrayList<>();
        for (double amount : amounts) {
            amountList.add(amount);
        }
        Collections.shuffle(amountList);
        for (int i = 0; i < count; i++) {
            amounts[i] = amountList.get(i);
        }
    }

    public synchronized double getAmount() throws RedPacketException {
        if (index >= count) {
            throw new RedPacketException("红包已经被抢完了！");
        }
        double amount = amounts[index] * (count - index) / remainCount(index);
        amount = Math.floor(amount * 100) / 100;
        claimedAmounts.put(Thread.currentThread().getName(),
                claimedAmounts.getOrDefault(Thread.currentThread().getName(), 0.0) + amount);
        index++;
        return amount;
    }

    private int remainCount(int index) {
        return count - index;
    }

    public synchronized double getClaimedAmount(String name) {
        return claimedAmounts.getOrDefault(name, 0.0);
    }

    public static class RedPacketException extends Exception {

        public RedPacketException(String message) {
            super(message);
        }
    }
}
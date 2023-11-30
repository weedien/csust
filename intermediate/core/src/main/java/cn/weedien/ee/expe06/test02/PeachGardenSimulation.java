package cn.weedien.ee.expe06.test02;

import java.util.Random;

public class PeachGardenSimulation {
    private static int totalPeaches = 100;

    public static void main(String[] args) {
        // 创建孙悟空线程
        Thread sunWukongThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100); // 孙悟空每隔100毫秒吃一个桃子
                    eatPeach();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建仙女线程
        Thread fairyThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500); // 仙女每隔500毫秒来数一次桃子
                    countPeaches();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建蟠桃数线程
        Thread peachTreeThread = new Thread(() -> {
            Random random = new Random();
            while (totalPeaches > 0) {
                try {
                    Thread.sleep(300); // 每隔300毫秒结出若干蟠桃
                    int newPeaches = random.nextInt(10);
                    growPeaches(newPeaches);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        sunWukongThread.start();
        fairyThread.start();
        peachTreeThread.start();
    }

    // 孙悟空吃桃子
    private static void eatPeach() {
        if (totalPeaches > 0) {
            totalPeaches--;
            System.out.println("孙悟空吃了一个桃子，剩余桃子数：" + totalPeaches);
        }
    }

    // 仙女数桃子
    private static void countPeaches() {
        System.out.println("仙女数了一次桃子，总桃子数：" + totalPeaches);
    }

    // 蟠桃树结桃子
    private static void growPeaches(int newPeaches) {
        totalPeaches += newPeaches;
        System.out.println("蟠桃树结出了 " + newPeaches + " 个桃子，总桃子数：" + totalPeaches);
    }
}

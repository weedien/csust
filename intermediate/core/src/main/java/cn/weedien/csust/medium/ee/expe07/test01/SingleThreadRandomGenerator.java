package cn.weedien.csust.medium.ee.expe07.test01;

import java.util.Random;

public class SingleThreadRandomGenerator {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int totalNumbers = 1000000;
        Random random = new Random();

        for (int i = 0; i < totalNumbers; i++) {
            random.nextInt(); // 生成随机数
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("单线程生成 " + totalNumbers + " 个随机数用时：" + totalTime + " 毫秒");
    }
}

package cn.weedien.ee.expe06.test01;

import java.util.Scanner;

public class PrimeNumberApp {
    public static void main(String[] args) {
        PrimeNumberApp primeNumberApp = new PrimeNumberApp();
        primeNumberApp.excute();
    }

    public void excute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        Long number = scanner.nextLong();
        System.out.println("输入了：" + number);
        while (number != 0) {
            PrimeNumberTester primeNumberTester = new PrimeNumberApp.PrimeNumberTester();

            // 统计时间
            long startTime = System.currentTimeMillis();
            primeNumberTester.countPrimeNumber(number);
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：" + (endTime - startTime) + "ms");

            System.out.println("请输入：");
            number = scanner.nextLong();
            System.out.println("输入了：" + number);
        }
        scanner.close();
    }

    public class PrimeNumberTester {
        /**
         * 测试输入的整数是否是质数
         */
        public boolean isPrimeNumber(Long number) {
            Long sqrNumber = (long) Math.sqrt(number);
            for (Long i = 2l; i <= sqrNumber; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 测试小于等于指定整数中质数的个数
         */
        public void countPrimeNumber(Long number) {
            Long count = 0l;
            for (Long i = 2l; i <= number; i++) {
                if (this.isPrimeNumber(i)) {
                    count++;
                }
            }
            System.out.println("小于:" + number + " 的质数个人数：" + count);
        }
    }
}

package cn.weedien;

// import java.text.DecimalFormat;
import java.util.Random;

public class RandomFlow {

    public static void main(String[] args) {
        Random rand = new Random(); // 创建一个Random对象
        /*
         * int[] data = new int[24]; // 声明一个长度为24的整数数组，表示24个小时的客流量数据
         * for (int i = 0; i < 24; i++) { // 遍历数组
         * double p = rand.nextDouble(); // 生成一个[0,1)之间的随机小数
         * if (i >= 8 && i <= 18) { // 如果是早上8点到下午6点之间，这个时间段客流量较大
         * if (p < 0.2) { // 以0.2的概率生成[1000,2000)之间的随机整数
         * data[i] = rand.nextInt(1000) + 1000;
         * } else if (p < 0.8) { // 以0.6的概率生成[2000,3000)之间的随机整数
         * data[i] = rand.nextInt(1000) + 2000;
         * } else { // 以0.2的概率生成[3000,4000)之间的随机整数
         * data[i] = rand.nextInt(1000) + 3000;
         * }
         * } else { // 如果是其他时间段，这个时间段客流量较小
         * if (p < 0.5) { // 以0.5的概率生成[100,200)之间的随机整数
         * data[i] = rand.nextInt(100) + 100;
         * } else if (p < 0.9) { // 以0.4的概率生成[200,300)之间的随机整数
         * data[i] = rand.nextInt(100) + 200;
         * } else { // 以0.1的概率生成[300,400)之间的随机整数
         * data[i] = rand.nextInt(100) + 300;
         * }
         * }
         * }
         */
        int[] times = { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
        double flow;

        for (int time : times) {
            double p = rand.nextDouble(); // 生成一个[0,1)之间的随机小数
            if (time >= 9 && time <= 12 || time >= 14 && time <= 17 || time >= 19 && time <= 21) { // 如果是早上8点到下午6点之间，这个时间段客流量较大
                if (p < 0.3) { // 以0.2的概率生成[1000,2000)之间的随机整数
                    flow = Math.round(rand.nextDouble() * 10 * 10) / 10.0;
                } else if (p < 0.9) { // 以0.6的概率生成[2000,3000)之间的随机整数
                    flow = Math.round((rand.nextDouble() * 10 + 10) * 10) / 10.0;
                } else { // 以0.2的概率生成[3000,4000)之间的随机整数
                    flow = Math.round((rand.nextDouble() * 10 + 20) * 10) / 10.0;
                }
            } else { // 如果是其他时间段，这个时间段客流量较小
                if (p < 0.6) { // 以0.5的概率生成[100,200)之间的随机整数
                    flow = Math.round(rand.nextDouble() * 5 * 10) / 10.0;
                } else if (p < 0.9) { // 以0.4的概率生成[200,300)之间的随机整数
                    flow = Math.round((rand.nextDouble() * 5 + 5) * 10) / 10.0;
                } else { // 以0.1的概率生成[300,400)之间的随机整数
                    flow = Math.round((rand.nextDouble() * 5 + 10) * 10) / 10.0;
                }
            }
            System.out.println(time + "==>" + flow);
        }

    }
}

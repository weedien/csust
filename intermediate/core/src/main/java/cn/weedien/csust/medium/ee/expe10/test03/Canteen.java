package cn.weedien.csust.medium.ee.expe10.test03;

import java.util.concurrent.Semaphore;

class Canteen {
    private static final int MAX_STUDENTS = 5; // 食堂窗口最大容纳学生数
    private Semaphore semaphore;

    public Canteen() {
        this.semaphore = new Semaphore(MAX_STUDENTS);
    }

    // 学生打饭
    public void getFood(String studentName) throws InterruptedException {
        semaphore.acquire(); // 学生尝试获取许可证，如果许可证不足，会阻塞在这里
        System.out.println(studentName + " 正在取餐...");
        // 模拟学生打饭的时间
        Thread.sleep(2000);
        System.out.println(studentName + " 取餐结束！");
        semaphore.release(); // 学生完成打饭，释放许可证
    }
}
package cn.weedien.csust.medium.ee.expe10.test03;

public class Main {
    public static void main(String[] args) {
        Canteen canteen = new Canteen();

        // 创建大量学生线程 A-Z
        for (int i = 65; i <= 90; i++) {
            Thread studentThread = new Thread(new Student("学生 " + (char) i, canteen));
            studentThread.start();
        }
    }
}

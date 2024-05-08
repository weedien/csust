package cn.weedien.csust.medium.ee.expe06.test03;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        Thread thread1 = new NumberThread();
        Thread thread2 = new LetterAThread();
        Thread thread3 = new LetterBThread();

        // 设置线程优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);

        try {
            thread2.start();
            thread3.start();
            thread2.join(); // 等待字母a线程结束
            thread1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class NumberThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.println(i);
            }
        }
    }

    static class LetterAThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.print("a");
                if (i == 50) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class LetterBThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.print("b");
                if (i == 50) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

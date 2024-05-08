package cn.weedien.csust.medium.ee.expe10.test04;

public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock();

        Thread secondThread = new Thread(() -> {
            try {
                while (true) {
                    clock.addSecond();
                    System.out.println(clock.getTime());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        secondThread.start();
    }
}

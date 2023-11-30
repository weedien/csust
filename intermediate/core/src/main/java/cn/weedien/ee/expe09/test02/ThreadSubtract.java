package cn.weedien.ee.expe09.test02;

// 删除list中的一个元素的线程
public class ThreadSubtract extends Thread {
    private Subtract c;

    public ThreadSubtract(Subtract c) {
        this.c = c;
    }

    public void run() {
        c.subtract();
    }
}

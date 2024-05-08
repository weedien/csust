package cn.weedien.csust.medium.ee.expe09.test02;

// 增加List中元素的线程
public class ThreadAdd extends Thread {
    private Add p;

    public ThreadAdd(Add p) {
        this.p = p;
    }

    public void run() {
        p.add();
    }
}

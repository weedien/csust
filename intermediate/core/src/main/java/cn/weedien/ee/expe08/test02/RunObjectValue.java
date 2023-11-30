package cn.weedien.ee.expe08.test02;

public class RunObjectValue {
    public static void main(String[] args) throws InterruptedException {
        ObjectValueService object = new ObjectValueService();
        ObjectValueThread a = new ObjectValueThread(object);
        a.setName("A");
        ObjectValueThread b = new ObjectValueThread(object);
        b.setName("B");
        a.start();
        Thread.sleep(50);
        b.start();
    }
}

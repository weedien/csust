package cn.weedien.ee.expe08.test02;

public class ObjectValueThread extends Thread {
    private ObjectValueService service;

    public ObjectValueThread(ObjectValueService s) {
        super();
        this.service = s;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

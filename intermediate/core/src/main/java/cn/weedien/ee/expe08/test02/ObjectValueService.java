package cn.weedien.ee.expe08.test02;

class ObjectValueService {

    private String lock = "123";

    public void testMethod() {

        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() +
                        " begin " + System.currentTimeMillis());
                // 改变lock对象的值
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

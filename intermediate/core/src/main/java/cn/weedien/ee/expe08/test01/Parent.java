package cn.weedien.ee.expe08.test01;

class Parent {
    protected int counter = 0;

    public synchronized void increment() {
        counter++;
    }
}

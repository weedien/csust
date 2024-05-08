package cn.weedien.csust.medium.ee.expe09.test02;

// 往list中增加一个字符串元素
public class Add {
    private String lock;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            System.out.println("list size=" + ValueObject.list.size());
            lock.notifyAll();
        }
    }
}
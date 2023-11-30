package cn.weedien.ee.expe05.test02;

class GunDog implements Dog {
    @Override
    public void bark() {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }
}
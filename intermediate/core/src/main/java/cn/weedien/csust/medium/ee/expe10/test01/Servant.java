package cn.weedien.csust.medium.ee.expe10.test01;

class Servant implements Runnable {
    private FruitPlate fruitPlate;

    public Servant(FruitPlate fruitPlate) {
        this.fruitPlate = fruitPlate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 只要果盘的数量少于5个，服务员就往果盘放入桃子
                if (fruitPlate.size() < 5) {
                    fruitPlate.putPeach();
                }
                // 模拟服务员的工作时间间隔
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
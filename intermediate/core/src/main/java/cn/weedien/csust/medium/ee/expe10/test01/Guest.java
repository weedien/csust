package cn.weedien.csust.medium.ee.expe10.test01;

class Guest implements Runnable {
    private FruitPlate fruitPlate;

    public Guest(FruitPlate fruitPlate) {
        this.fruitPlate = fruitPlate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 只要果盘里有桃子，客人就从果盘中取出一个桃子
                if (fruitPlate.size() > 0) {
                    fruitPlate.takePeach();
                }
                // 模拟客人的用餐时间间隔
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
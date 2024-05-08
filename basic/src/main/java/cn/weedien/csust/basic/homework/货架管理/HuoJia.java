package cn.weedien.csust.basic.homework.货架管理;

// 货架类
class HuoJia {
    private final huojiaApp[] apps; // 存储插装的小程序
    private int count; // 当前插装小程序的数量

    public HuoJia() {
        apps = new huojiaApp[10];
        count = 0;
    }

    // 添加小程序
    public void add(huojiaApp app) {
        if (count < 10) {
            apps[count++] = app;
        }
    }

    // 运行所有小程序
    public void run() {
        for (int i = 0; i < count; i++) {
            apps[i].shangjia();
            apps[i].cunfang();
            apps[i].quzou();
            System.out.println();
        }
    }
}
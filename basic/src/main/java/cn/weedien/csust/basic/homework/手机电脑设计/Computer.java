package cn.weedien.csust.basic.homework.手机电脑设计;

// 电脑类
class Computer implements Net, Elec {
    private boolean powerOn; // 电脑开关状态

    public void getInterNet() {
        System.out.println("电脑正在上网");
    }

    public void on() {
        if (!powerOn) {
            System.out.println("电脑开机");
            powerOn = true;
        }
    }

    public void off() {
        if (powerOn) {
            System.out.println("电脑关机");
            powerOn = false;
        }
    }
}
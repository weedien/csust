package cn.weedien.csust.basic.homework.手机电脑设计;

// 手机类
class MobilePhone extends Telephone implements Net, Elec {
    private boolean powerOn; // 手机开关状态
    private String name; // 手机名称

    public MobilePhone(String name) {
        this.name = name;
        powerOn = false;
    }

    public void sendCall(String name) {
        System.out.println(this.name + "打电话给" + name);
    }

    public void getCall(String name) {
        System.out.println(this.name + "接到来自" + name + "的电话");
    }

    public void sendMessage(String name, String message) {
        System.out.println(this.name + "向" + name + "发送短信：" + message);
    }

    public void getInterNet() {
        System.out.println(this.name + "正在上网");
    }

    public void on() {
        if (!powerOn) {
            System.out.println(this.name + "开机");
            powerOn = true;
        }
    }

    public void off() {
        if (powerOn) {
            System.out.println(this.name + "关机");
            powerOn = false;
        }
    }
}
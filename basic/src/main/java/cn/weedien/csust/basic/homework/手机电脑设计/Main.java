package cn.weedien.csust.basic.homework.手机电脑设计;

// 测试程序
public class Main {
    public static void main(String[] args) {
        MobilePhone phone = new MobilePhone("小米手机");
        phone.on();
        phone.sendCall("张三");
        phone.getCall("李四");
        phone.sendMessage("王五", "你好！");
        phone.getInterNet();
        phone.off();

        Computer pc = new Computer();
        pc.on();
        pc.getInterNet();
        pc.off();
    }
}

package cn.homework.货架管理;

// 测试程序
public class App1 {
    public static void main(String[] args) {
        HuoJia a = new HuoJia();
        ShouJi sj = new ShouJi();
        BiJiBen bjb = new BiJiBen();
        JingBuAnMoYi amy = new JingBuAnMoYi();
        a.add(sj);
        a.add(bjb);
        a.add(amy);
        a.run();
    }
}
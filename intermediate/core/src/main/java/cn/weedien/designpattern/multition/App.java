package cn.weedien.designpattern.multition;

public class App {
    public static void main(String[] args) {
        System.out.println("第一个季节是：" + Season.getInstance(SeasonEnum.SPRING));
        System.out.println("第二个季节是：" + Season.getInstance(SeasonEnum.SUMMER));
        System.out.println("第三个季节是：" + Season.getInstance(SeasonEnum.AUTUMN));
        System.out.println("第四个季节是：" + Season.getInstance(SeasonEnum.WINTER));
    }

}

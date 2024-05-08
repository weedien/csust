package cn.weedien.csust.medium.designpattern.simplefactory;

public class App {

    /**
     * Program main entry point.
     */
    public static void main(String[] args) {
        System.out.println("炼金术士开始了他的工作！");
        Coin coin1 = CoinFactory.getCoin(CoinType.COPPER);
        Coin coin2 = CoinFactory.getCoin(CoinType.GOLD);
        System.out.println(coin1.getDescription());
        System.out.println(coin2.getDescription());
    }
}

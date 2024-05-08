package cn.weedien.csust.medium.designpattern.simplefactory;

public class CopperCoin implements Coin {

    static final String DESCRIPTION = "这是一枚铜币。";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

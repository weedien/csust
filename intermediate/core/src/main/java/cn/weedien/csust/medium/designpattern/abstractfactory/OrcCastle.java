package cn.weedien.csust.medium.designpattern.abstractfactory;

public class OrcCastle implements Castle {

    static final String DESCRIPTION = "这是兽人族的城堡！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
package cn.weedien.designpattern.abstractfactory;

public class OrcKing implements King {

    static final String DESCRIPTION = "这是兽人族的国王！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

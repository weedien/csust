package cn.weedien.designpattern.abstractfactory;

public class OrcArmy implements Army {

    static final String DESCRIPTION = "这是兽人族的军队！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
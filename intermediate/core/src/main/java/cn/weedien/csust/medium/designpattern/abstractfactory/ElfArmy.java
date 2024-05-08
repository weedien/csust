package cn.weedien.csust.medium.designpattern.abstractfactory;

public class ElfArmy implements Army {

    static final String DESCRIPTION = "这是精灵族的军队！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

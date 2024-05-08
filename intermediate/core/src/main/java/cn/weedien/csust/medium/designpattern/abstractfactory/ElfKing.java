package cn.weedien.csust.medium.designpattern.abstractfactory;

public class ElfKing implements King {

    static final String DESCRIPTION = "这是精灵族的国王！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
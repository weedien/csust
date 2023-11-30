package cn.weedien.designpattern.abstractfactory;

public class ElfCastle implements Castle {

    static final String DESCRIPTION = "这是精灵族的城堡！";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

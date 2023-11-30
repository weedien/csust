package cn.weedien.designpattern.multition;

import java.util.Map;

public class Season {

    private final String name;

    private final String description;

    private static final Map<SeasonEnum, Season> seasons;

    static {
        seasons = Map.of(
                SeasonEnum.SPRING, new Season("春天", "温暖"),
                SeasonEnum.SUMMER, new Season("夏天", "炎热"),
                SeasonEnum.AUTUMN, new Season("秋天", "凉爽"),
                SeasonEnum.WINTER, new Season("冬天", "寒冷"));
    }

    private Season(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Season getInstance(SeasonEnum seasonEnum) {
        return seasons.get(seasonEnum);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description + "的" + name;
    }
}

package cn.weedien;

import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TimePeriod {
    public static void main(String[] args) {
        List<String> timePeriods = new ArrayList<>();

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(21, 0);
        Duration interval = Duration.ofMinutes(120);
        timePeriods.add(start.toString());
        while (start.isBefore(end)) {
            start = start.plus(interval);
            timePeriods.add(start.toString());
        }
        System.out.println(timePeriods);
    }
}

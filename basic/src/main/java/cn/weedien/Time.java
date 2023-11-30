package cn.weedien;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Time {
    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 往后数七天 存入数组
        List<String> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(dateFormat.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }

        System.out.println(weekDays);

    }
}

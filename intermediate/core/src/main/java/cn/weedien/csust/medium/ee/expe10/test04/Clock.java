package cn.weedien.csust.medium.ee.expe10.test04;

import java.util.Calendar;

class Clock {
    private int seconds;
    private int minutes;
    private int hours;

    public Clock() {
        // 获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        this.seconds = calendar.get(Calendar.SECOND);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.hours = calendar.get(Calendar.HOUR_OF_DAY);
    }

    // 增加秒针
    public synchronized void addSecond() throws InterruptedException {
        while (seconds >= 60) {
            wait();
        }

        Thread.sleep(1000);

        seconds++;
        if (seconds == 60) {
            seconds = 0;
            addMinute(); // 秒针走到60，增加分钟
        }

        notifyAll();
    }

    // 增加分针
    public synchronized void addMinute() throws InterruptedException {
        while (minutes >= 60) {
            wait();
        }

        minutes++;
        if (minutes == 60) {
            minutes = 0;
            addHour(); // 分针走到60，增加小时
        }

        notifyAll();
    }

    // 增加时针
    public synchronized void addHour() throws InterruptedException {
        while (hours >= 24) {
            wait();
        }

        hours++;
        if (hours == 24) {
            hours = 0; // 时针走到24，重置为0
        }

        notifyAll();
    }

    // 获取当前时间的字符串表示
    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

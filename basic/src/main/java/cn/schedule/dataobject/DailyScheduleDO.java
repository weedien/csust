package cn.schedule.dataobject;

import java.util.List;

public class DailyScheduleDO {
    // 编号
    private Long id;
    // 日期
    private String date;
    // 店铺id
    private Long shopId;
    // 营业时间
    private String startTime;
    // 关店时间
    private String endTime;
    // 时间间隔 单位为分钟
    private int duration;
    // 排班信息 每个时间段对应的员工id [[1,2,3],[4,5,6]...]
    private List<List<Long>> scheduleInfo;

}
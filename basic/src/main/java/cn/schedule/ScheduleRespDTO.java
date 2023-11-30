package cn.schedule;

import java.util.List;

import cn.schedule.dataobject.DailyScheduleDO;
import cn.schedule.dataobject.StaffWorktimeDO;

public class ScheduleRespDTO {
    // 门店id
    private Long shopId;

    // 一周开始的那天（周一） 从这天开始排班 2023-03-27
    private String startOfWeek;

    // 每周排班信息 7天 周一到周日
    private List<DailyScheduleDO> weeklySchedule;

    // 员工一周的排班信息 7天 周一到周日
    private List<StaffWorktimeDO> weeklyScheduleByStaff;

}
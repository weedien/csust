package cn.schedule;

import java.util.List;

import cn.schedule.dataobject.PredataDO;
import cn.schedule.dataobject.PreferDO;
import cn.schedule.dataobject.RuleDO;

public class ScheduleReqDTO {
    // 门店id
    private Long shopId;

    // 一周开始的那天（周一） 2023-03-27
    private String startOfWeek;

    // 时间间隔 单位为分钟 排班30展示60
    private int duration;

    // 预测数据信息
    private List<PredataDO> predataList;

    // 门店排班规则信息
    private List<RuleDO> RuleList;

    // 员工信息
    private List<StaffDTO> staffList;

    public static class StaffDTO {
        // 员工id
        private Long id;
        // 员工姓名
        private String name;
        // 员工职位
        private String post;
        // 偏好信息
        private List<PreferDO> preferList;
    }

}
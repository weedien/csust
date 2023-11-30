package cn.schedule.dataobject;

import java.math.BigDecimal;

public class PredataDO {

    /**
     * 业务预测数据ID
     */
    private Long id;
    /**
     * 门店ID
     */
    private Long shopId;
    /**
     * 日期
     */
    private String date;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 预测顾客流量
     */
    private BigDecimal preFlow;

}
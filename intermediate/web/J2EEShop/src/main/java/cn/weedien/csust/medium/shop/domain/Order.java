package cn.weedien.csust.medium.shop.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户订单对象
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class Order implements Serializable {
    /**
     * 该订单中的订单项
     */
    List<OrderItem> orderItems = new ArrayList<>();
    /**
     * 该订单的订单号
     */
    private String oid;
    /**
     * 下单时间
     */
    private Timestamp ordertime;
    /**
     * 该订单的总金额
     */
    private double total;
    /**
     * 订单支付状态，1代表未付款，2代表已付款未发货，3代表已发货为收货，4代表收货订单结束
     */
    private int state;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人
     */
    private String name;
    /**
     * 收货人电话
     */
    private String telephone;
    /**
     * 该订单属于哪个用户
     */
    private User user;
}

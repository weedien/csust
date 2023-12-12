package cn.weedien.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户订单项实体类
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class OrderItem implements Serializable {
    /**
     * 订单项的id
     */
    private String itemid;
    /**
     * 订单项内商品的购买数量
     */
    private int count;
    /**
     * 订单项小计
     */
    private double subtotal;
    /**
     * 订单项内部的商品
     */
    private Product product;
    /**
     * 该订单项属于哪个订单
     */
    private String oid;

    private String pid;
}

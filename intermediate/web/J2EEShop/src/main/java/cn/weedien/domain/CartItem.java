package cn.weedien.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车项实体类
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class CartItem implements Serializable {
    /**
     * 商品项
     */
    private Product product;
    /**
     * 购买此商品的数量
     */
    private int count;
    /**
     * 购买此商品的总价
     */
    private double subtotal;

    /**
     * 总价通过计算得到
     */
    public double getSubtotal() {
        this.subtotal = count * product.getShop_price();
        return subtotal;
    }
}

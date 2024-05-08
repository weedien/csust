package cn.weedien.csust.medium.shop.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 *
 * @author weedien
 * @date 2023/12/10
 */
public class Cart implements Serializable {
    /**
     * 购物车中所有内容，通过一个map集合储存，键是商品id，值是CartItem
     */
    private final Map<String, CartItem> map = new LinkedHashMap<>();
    /**
     * 购物车中所有商品的总价
     */
    private double total;

    /**
     * 构造方法
     */
    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    /**
     * total的获取方法，通过计算得到
     */
    public double getTotal() {
        total = 0;
        for (Map.Entry<String, CartItem> entry : map.entrySet()) {
            CartItem cartItem = entry.getValue();
            total += cartItem.getSubtotal();
        }
        return total;
    }

    /**
     * 添加到购物车，商品和数量
     */
    public void addCart(Product product, int count) {
        if (product == null) {
            return;
        }

        CartItem cartItem = map.get(product.getPid());
        // 如果购物车中没有此商品
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(count);
            map.put(product.getPid(), cartItem);
        } else {
            // 如果购物车中已有此商品，只则添加数量
            cartItem.setCount(cartItem.getCount() + count);
        }
    }

    /**
     * 将商品项从购物车中删除
     */
    public void removeCart(String id) {
        CartItem cartItem = map.remove(id);
        total -= cartItem.getSubtotal();
    }

    /**
     * 将购物车清空
     */
    public void clearCart() {
        map.clear();
        total = 0;
    }

}

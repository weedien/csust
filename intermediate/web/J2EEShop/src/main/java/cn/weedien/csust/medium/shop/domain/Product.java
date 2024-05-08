package cn.weedien.csust.medium.shop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * creater:litiecheng
 * createDate:2017-9-1
 * discription:商品JavaBean
 * indetail:
 */
@Data
public class Product implements Serializable {
    private String pid;
    private String pname;
    private double market_price;
    private double shop_price;
    private String pimage;
    private Date pdate;
    private int is_hot;
    private String pdesc;
    private int pflag;
    private String cid;
}

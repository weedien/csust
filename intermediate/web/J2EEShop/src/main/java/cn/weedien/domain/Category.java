package cn.weedien.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类实体类
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class Category implements Serializable {
    private String cid;
    private String cname;
}

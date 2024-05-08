package cn.weedien.csust.medium.shop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class User implements Serializable {

    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private Date birthday;
    private String sex;
    private int state;

}

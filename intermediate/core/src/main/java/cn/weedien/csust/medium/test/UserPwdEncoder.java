package cn.weedien.csust.medium.test;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class UserPwdEncoder {
    /**
     * 密码加盐，更推荐的做法是每个用户都使用独立的盐，提高安全性
     */
    private String salt = "tech_π";

    private Integer saltIndex = 3;

    public static void main(String[] args) {
        // 对密码进行加密
        UserPwdEncoder encoder = new UserPwdEncoder();
        String plainPwd = "admin";
        String encPwd = encoder.encPwd(plainPwd);
        System.out.println("明文密码：" + plainPwd);
        System.out.println("加密后密码：" + encPwd);
    }

    public boolean match(String plainPwd, String encPwd) {
        return Objects.equals(encPwd(plainPwd), encPwd);
    }

    /**
     * 明文密码处理
     *
     * @param plainPwd
     * @return
     */
    public String encPwd(String plainPwd) {
        if (plainPwd.length() > saltIndex) {
            plainPwd = plainPwd.substring(0, saltIndex) + salt + plainPwd.substring(saltIndex);
        } else {
            plainPwd = plainPwd + salt;
        }
        return DigestUtils.md5DigestAsHex(plainPwd.getBytes(StandardCharsets.UTF_8));
    }

}
package cn.weedien.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class Md5Util {
    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("没有这个md5算法！");
            return input;
        }
        md.update(input.getBytes());

        byte[] digest = md.digest();

        // 将字节数组转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

}

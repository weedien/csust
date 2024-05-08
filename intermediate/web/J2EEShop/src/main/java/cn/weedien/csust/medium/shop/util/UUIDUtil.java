package cn.weedien.csust.medium.shop.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}

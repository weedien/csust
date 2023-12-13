package cn.weedien.util;

import javax.servlet.http.Cookie;

/**
 * Cookie 工具类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class CookieUtil {
    public static Cookie findCookie(Cookie[] allCookie, String cookieName) {
        if (cookieName == null) {
            return null;
        }
        if (allCookie != null) {
            for (Cookie c : allCookie) {
                if (cookieName.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}

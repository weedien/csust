package cn.weedien.csust.medium.shop.util;

import cn.weedien.csust.medium.shop.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 用户Bean工具类
 * <p>
 * 将数据封装到User对象中
 *
 * @author weedien
 * @date 2023/12/10
 */
public class UserBeanUtil {
    public static void populate(User user, Map<String, String[]> parameterMap) throws InvocationTargetException, IllegalAccessException {

        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class aClass, Object value) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = null;
                try {
                    parse = format.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return parse;
            }
        }, Date.class);

        BeanUtils.populate(user, parameterMap);
    }
}

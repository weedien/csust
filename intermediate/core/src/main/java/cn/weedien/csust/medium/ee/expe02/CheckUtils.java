package cn.weedien.csust.medium.ee.expe02;

import java.util.List;

public class CheckUtils {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("0?(13|14|15|18|17)[0-9]{9}");
    }

    // 校验分组信息
    public static boolean isValidGroup(String group, String[] groups) {
        for (String validGroup : groups) {
            if (group.equals(validGroup)) {
                return true;
            }
        }
        return false;
    }

    // 校验姓名是否重复
    public static boolean isValidName(String name, List<String> nameList) {
        for (String element : nameList) {
            if (name.equals(element)) {
                return false;
            }
        }
        return true;
    }
}

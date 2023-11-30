package cn.homework.设计MyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyString {
    private String msString;

    public MyString(String s) {
        msString = s;
    }

    public String[] mySplit(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msString);
        List<String> list = new ArrayList<>();
        int lastIndex = 0;
        while (matcher.find()) {
            String match = matcher.group();
            if (matcher.start() > lastIndex) {
                list.add(msString.substring(lastIndex, matcher.start()));
            }
            list.add(match);
            lastIndex = matcher.end();
        }
        if (lastIndex < msString.length()) {
            list.add(msString.substring(lastIndex));
        }
        return list.toArray(new String[0]);
    }

    public static String[] mySplit(String s, String regex) {
        MyString ms = new MyString(s);
        return ms.mySplit(regex);
    }

    public int length() {
        return msString.length();
    }

    public char charAt(int index) {
        return msString.charAt(index);
    }

    public boolean equals(MyString other) {
        return msString.equals(other.msString);
    }

    public MyString substring(int beginIndex, int endIndex) {
        return new MyString(msString.substring(beginIndex, endIndex));
    }

    public static void main(String[] args) {
        String s = "asb#?Gfj%123*hj";
        MyString ms = new MyString(s);

        // 调用实例方法 mySplit
        String[] result1 = ms.mySplit("[#?%*]");
        System.out.println(Arrays.toString(result1)); // 输出 [asb, #, ?, Gfj, %, 123, *, hj]

        // 调用静态方法 mySplit
        String[] result2 = MyString.mySplit(s, "[#?%*]");
        System.out.println(Arrays.toString(result2)); // 输出 [asb, #, ?, Gfj, %, 123, *, hj]
    }
}
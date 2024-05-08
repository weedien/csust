### 1. 题目要求

定义自己的String类MyString，该类有一个数据域成员：String msString；一个方法成员：mySplit()
用于返回给定分隔符分隔的字符串数组，要求这个数组中含有这些分隔符，方法声明如下：

Public static String[] mySplit(String s,String regex);s给定母串，regex为给定正则表达式

Public String[] mySplit(String regex);返回当前字符串以指定正则表达式（regex）分离的字符串数组

该类的其他方法由你自己根据需要确定。

例子：mySplit（"asb#?Gfj%123*hj","[#?%*]"）；运行返回的字符串数组为：asd、#、？、Gfj、&、123、*、hj

### 2. 代码实现

```java
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
```

### 3. 结果展示

![](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/Pasted image 20230417163025.png)
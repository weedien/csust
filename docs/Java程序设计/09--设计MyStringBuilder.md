### 1. 题目要求

（实现StringBuilder类）在Java库中提供了String Builder类，给出你自己定义的String Builder类：MyStringBuilder类，并实现下面方法并设计主类测试她：

- Public MyStringBuilder（String s）；
- Public MyStringBuilder append(MyStringBuilder s);
- Public MyStringBuilder append(int i);
- Public int length();
- Public char charAt(int index);
- Public MyStringBuilder toLowerCase();
- Public MyStringBuilder subString(int begin,int end);
- Public String toString();

### 2. 代码实现

```java
public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder(String s) {
        value = s.toCharArray();
        count = s.length();
    }

    public MyStringBuilder append(MyStringBuilder s) {
        int newCount = count + s.length();
        char[] newValue = new char[newCount];
        System.arraycopy(value, 0, newValue, 0, count);
        System.arraycopy(s.value, 0, newValue, count, s.length());
        count = newCount;
        value = newValue;
        return this;
    }

    public MyStringBuilder append(int i) {
        return append(new MyStringBuilder(String.valueOf(i)));
    }

    public MyStringBuilder append(char ch) {
        return append(new MyStringBuilder(String.valueOf(ch)));
    }

    public int length() {
        return count;
    }

    public char charAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return value[index];
    }

    public MyStringBuilder toLowerCase() {
        MyStringBuilder newBuilder = new MyStringBuilder("");
        for (int i = 0; i < count; i++) {
            newBuilder.append(Character.toLowerCase(value[i]));
        }
        return newBuilder;
    }

    public MyStringBuilder subString(int begin, int end) {
        if (begin < 0 || end > count || begin > end) {
            throw new IndexOutOfBoundsException();
        }
        char[] subValue = new char[end - begin];
        System.arraycopy(value, begin, subValue, 0, end - begin);
        return new MyStringBuilder(new String(subValue));
    }

    public String toString() {
        return new String(value, 0, count);
    }

    public static void main(String[] args) {
        MyStringBuilder builder1 = new MyStringBuilder("Hello");
        MyStringBuilder builder2 = new MyStringBuilder(" world");
        builder1.append(builder2);
        System.out.println(builder1.toString()); // "Hello world"
        builder1.append(123);
        System.out.println(builder1.toString()); // "Hello world123"
        System.out.println(builder1.length()); // 14
        System.out.println(builder1.charAt(6)); // 'w'
        System.out.println(builder1.toLowerCase()); // "hello world123"
        System.out.println(builder1.subString(6, 11).toString()); // "world"
    }
}
```

### 3. 结果展示

![](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/Pasted image 20230409210018.png)
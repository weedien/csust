package cn.weedien.csust.basic.homework.设计MyStringBuilder;

public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder(String s) {
        value = s.toCharArray();
        count = s.length();
    }

    public static void main(String[] args) {
        MyStringBuilder builder1 = new MyStringBuilder("Hello");
        MyStringBuilder builder2 = new MyStringBuilder(" world");
        builder1.append(builder2);
        System.out.println(builder1); // "Hello world"
        builder1.append(123);
        System.out.println(builder1); // "Hello world123"
        System.out.println(builder1.length()); // 14
        System.out.println(builder1.charAt(6)); // 'w'
        System.out.println(builder1.toLowerCase()); // "hello world123"
        System.out.println(builder1.subString(6, 11).toString()); // "world"
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
}

package cn.homework.统计字符串连续字符;

import java.util.Scanner;

public class MaxContinuousChar {
    public static void main(String[] args) {
        // 读取用户输入的字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String s = sc.nextLine();
        sc.close();

        // 调用方法，输出运行结果
        String result = countMaxContinusChar(s);
        System.out.println(result);
    }

    public static String countMaxContinusChar(String s) {

        // 如果字符串为空或者长度为0，返回空字符串
        if (s == null || s.length() == 0) {
            return "";
        }

        char curChar = s.charAt(0); // 当前字符
        int curCount = 1; // 当前字符的出现次数
        char maxChar = curChar; // 最长的字符
        int maxCount = curCount; // 最长的字符的长度

        for (int i = 1; i < s.length(); i++) {
            // 比较当前字符和上一个字符是否相同
            if (s.charAt(i) == curChar) {
                // 如果相同，增加当前字符的出现次数
                curCount++;
            } else {
                // 如果不同，重置当前字符的出现次数为1，并更新当前字符
                curCount = 1;
                curChar = s.charAt(i);
            }
            // 比较当前字符的出现次数和最长的字符的长度
            if (curCount > maxCount) {
                // 更新最长的字符和长度
                maxChar = curChar;
                maxCount = curCount;
            }
        }

        return maxChar + " " + maxCount;
    }
}

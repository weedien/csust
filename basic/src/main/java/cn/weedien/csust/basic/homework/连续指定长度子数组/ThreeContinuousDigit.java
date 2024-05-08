package cn.weedien.csust.basic.homework.连续指定长度子数组;

import java.util.Arrays;

public class ThreeContinuousDigit {
    private int[] arrayIndexes;
    private int[] numbers;
    private int subArrayNumber;
    private int[] sourceArray;

    // 构造方法
    public ThreeContinuousDigit(int[] sourceArray) {
        this.sourceArray = sourceArray;
        this.arrayIndexes = new int[sourceArray.length];
        this.numbers = new int[sourceArray.length];
        this.subArrayNumber = 0;
    }

    public static void main(String[] args) {
        int[] sourceArray = {1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7};
        ThreeContinuousDigit tcd = new ThreeContinuousDigit(sourceArray);
        tcd.locationContinuousDigit();

        System.out.println("原数组: " + Arrays.toString(sourceArray) + "\n");
        System.out.println("连续出现3次或3次以上的子数组个数: " + tcd.getSubArrayNumber() + "\n");

        System.out.println("输出所有连续子数组：");
        for (int i = 0; i < tcd.getSubArrayNumber(); i++) {
            int startIndex = tcd.getArrayIndexs()[i];
            int length = tcd.getNumbers()[i];
            System.out.println(
                    "起始下标: " + startIndex + ", 长度: " + length);
            System.out.println("连续子数组：" + Arrays.toString(
                    Arrays.copyOfRange(tcd.getSourceArray(), startIndex, startIndex + length)) + "\n");
        }
    }

    // 查找连续出现3次或3次以上的子数组
    public ThreeContinuousDigit locationContinuousDigit() {
        int count = 1;
        for (int i = 1; i < sourceArray.length; i++) {
            // 相邻两个元素进行比较，如果相等，则计数器加1
            if (sourceArray[i] == sourceArray[i - 1]) {
                count++;
            } else {
                // 如果不相等，且计数器大于等于3，则说明找到了连续出现3次或3次以上的子数组
                if (count >= 3) {
                    arrayIndexes[subArrayNumber] = i - count;
                    numbers[subArrayNumber] = count;
                    subArrayNumber++;
                }
                // 重置计数器
                count = 1;
            }
        }
        // 处理最后一个子数组
        if (count >= 3) {
            arrayIndexes[subArrayNumber] = sourceArray.length - count;
            numbers[subArrayNumber] = count;
            subArrayNumber++;
        }
        return this;
    }

    public int[] getArrayIndexs() {
        return arrayIndexes;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getSubArrayNumber() {
        return subArrayNumber;
    }

    public int[] getSourceArray() {
        return sourceArray;
    }
}
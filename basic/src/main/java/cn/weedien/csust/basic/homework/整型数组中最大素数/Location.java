package cn.weedien.csust.basic.homework.整型数组中最大素数;

import java.util.Arrays;

public class Location {
    private int row;
    private int column;
    private int maxPrimeValue;
    private final int[][] element;

    // 构造方法
    public Location(int[][] element) {
        this.element = element;
        findMaxPrime();
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 4}, {5, 7, 8}, {3, 6, 9}};
        Location location = new Location(arr);
        System.out.println("二维数组：" + Arrays.deepToString(arr));
        System.out.println("最大素数：" + location.getMaxPrimeValue() + "\t" + "位置：("
                + location.getRow() + ", " + location.getColumn() + ")");
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getMaxPrimeValue() {
        return maxPrimeValue;
    }

    // 找出二维数组中最大的素数，并记录其位置
    private void findMaxPrime() {
        int maxPrime = -1;
        int maxPrimeRow = -1;
        int maxPrimeCol = -1;
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[i].length; j++) {
                if (isPrime(element[i][j]) && element[i][j] > maxPrime) {
                    maxPrime = element[i][j];
                    maxPrimeRow = i;
                    maxPrimeCol = j;
                }
            }
        }
        maxPrimeValue = maxPrime;
        row = maxPrimeRow;
        column = maxPrimeCol;
    }

    // 判断一个数是否为素数
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
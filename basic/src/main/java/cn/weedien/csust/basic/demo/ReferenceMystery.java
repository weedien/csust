package cn.weedien.csust.basic.demo;

import java.util.Arrays;

class ReferenceMystery {
    public static void main(String[] args) {
        int x = 1;
        int[] a = new int[4];
        x++;
        a[x - 1] = 3;
        mystery(x, a);
        System.out.println(x + " " + Arrays.toString(a));
        x++;
        a[x - 1] = 2;
        mystery(x, a);
        System.out.println(x + " " + Arrays.toString(a));
    }

    public static void mystery(int x, int[] a) {
        a[x]++;
        x--;
        a[x - 1] = a[x + 1];
        System.out.println(x + " " + Arrays.toString(a));
    }
}

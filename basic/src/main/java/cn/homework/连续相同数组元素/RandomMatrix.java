package cn.homework.连续相同数组元素;

import java.util.Random;
import java.util.Scanner;

public class RandomMatrix {

    public static void main(String[] args) {
        // 读取用户输入的方阵行数
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入方阵的行数：");
        int n = sc.nextInt();
        sc.close();

        // 使用Random对象来生成随机的0或1
        Random rand = new Random();

        // 创建一个二维数组来存储方阵的元素
        int[][] matrix = new int[n][n];

        // 遍历方阵，为每个元素赋值为0或1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2); // 生成0或1
            }
        }

        // 打印方阵
        System.out.println("随机生成的方阵为：");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // 记录是否找到整行或整列都是0或1
        boolean rowFound = false;
        boolean colFound = false;

        // 检查每一行是否都是0或1
        for (int i = 0; i < n; i++) {
            int sumRow = 0; // 每一行的元素之和
            for (int j = 0; j < n; j++) {
                sumRow += matrix[i][j];
            }
            if (sumRow == 0 || sumRow == n) { // 如果每一行的元素之和为0或n，说明该行都是0或1
                System.out.println("第" + (i + 1) + "行都是" + matrix[i][0]);
                rowFound = true;
            }
        }

        // 如果没有找到整行都是0或1，打印提示信息
        if (!rowFound) {
            System.out.println("没有整行都是0或1");
        }

        // 检查每一列是否都是0或1
        for (int j = 0; j < n; j++) {
            int sumCol = 0; // 记录每一列的元素之和
            for (int i = 0; i < n; i++) {
                sumCol += matrix[i][j];
            }
            if (sumCol == 0 || sumCol == n) { // 如果每一列的元素之和为0或n，说明该列都是0或1
                System.out.println("第" + (j + 1) + "列都是" + matrix[0][j]);
                colFound = true;
            }
        }

        // 如果没有找到整列都是0或1，打印提示信息
        if (!colFound) {
            System.out.println("没有整列都是0或1");
        }

        // 记录两条对角线的元素之和
        int sumDiag1 = 0;
        int sumDiag2 = 0;

        // 遍历对角线的元素
        for (int i = 0; i < n; i++) {
            sumDiag1 += matrix[i][i]; // 主对角线
            sumDiag2 += matrix[i][n - i - 1]; // 副对角线
        }

        // 判断是否都是0或1
        if (sumDiag1 == 0 || sumDiag1 == n) {
            System.out.println("主对角线都是" + matrix[0][0]);
        } else {
            System.out.println("主对角线没有相同数字");
        }
        if (sumDiag2 == 0 || sumDiag2 == n) {
            System.out.println("副对角线都是" + matrix[0][n - 1]);
        } else {
            System.out.println("副对角线没有相同数字");
        }
    }
}

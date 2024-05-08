package cn.weedien.csust.medium.ee.expe04.test01;

import java.util.Scanner;

public class Test {
    private static final String PACKAGE_NAME = Test.class.getPackage().getName() + ".";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("请输入您要进行的计算(Add/Sub):");
            String operation = scanner.nextLine();

            Calculator calculator = null;

            // 使用反射根据用户输入的操作名称来创建对应的计算类对象
            try {
                Class<?> operationClass = Class.forName(PACKAGE_NAME + operation);
                calculator = (Calculator) operationClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("无效的操作名称");
                return;
            }

            System.out.println("请输入两个操作数（使用换行符分隔）：");
            double num1 = scanner.nextDouble();
            double num2 = scanner.nextDouble();

            // 调用计算类的calculate方法来执行计算
            double result = calculator.calculate(num1, num2);

            System.out.println(num1 + " " + operation + " " + num2 + " = " + result);
        }
    }
}

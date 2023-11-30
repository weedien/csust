package cn.homework.异常处理;

public class Main {
    public static void main(String[] args) {

        System.out.println("Test Case 1:");
        String hexString1 = "1A2B3C";
        try {
            String decString = HexConverter.hexToDecString(hexString1);
            System.out.println("Hex string " + hexString1 + " is equivalent to decimal string " + decString);
        } catch (HexFormatException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Test Case 2:");
        String hexString2 = "1A2B3C4G";
        try {
            String decString = HexConverter.hexToDecString(hexString2);
            System.out.println("Hex string " + hexString2 + " is equivalent to decimal string " + decString);
        } catch (HexFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
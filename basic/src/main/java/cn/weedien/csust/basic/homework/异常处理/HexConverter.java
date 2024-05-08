package cn.weedien.csust.basic.homework.异常处理;

public class HexConverter {
    public static String hexToDecString(String hexString) throws HexFormatException {
        if (!hexString.matches("[0-9A-Fa-f]+")) {
            throw new HexFormatException("Invalid hex string: " + hexString);
        }
        return String.valueOf(Long.parseLong(hexString, 16));
    }
}


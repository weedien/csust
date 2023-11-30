package cn.weedien;

class Env {
    public static void main(String[] args) {
        String logFilePath = System.getenv("LOG_FILE");
        String logPath = System.getenv("LOG_PATH");
        String logTemp = System.getenv("LOG_TEMP");
        String tmpDirPath = System.getProperty("java.io.tmpdir");

        // 输出上面的内容
        System.out.println("LOG_FILE: " + logFilePath);
        System.out.println("LOG_PATH: " + logPath);
        System.out.println("LOG_TEMP: " + logTemp);
        System.out.println("java.io.tmpdir: " + tmpDirPath);

    }
}
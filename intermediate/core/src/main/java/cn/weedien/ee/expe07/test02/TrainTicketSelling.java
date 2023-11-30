package cn.weedien.ee.expe07.test02;

public class TrainTicketSelling {
    public static void main(String[] args) {
        // 创建一个共享的列车票资源
        TrainTicket trainTicket = new TrainTicket(200);

        // 创建三个售票窗口
        TicketWindow window1 = new TicketWindow(trainTicket, "窗口1");
        TicketWindow window2 = new TicketWindow(trainTicket, "窗口2");
        TicketWindow window3 = new TicketWindow(trainTicket, "窗口3");

        // 启动售票窗口线程
        window1.start();
        window2.start();
        window3.start();
    }
}

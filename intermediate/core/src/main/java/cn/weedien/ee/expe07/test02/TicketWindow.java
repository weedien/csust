package cn.weedien.ee.expe07.test02;

class TicketWindow extends Thread {
    private TrainTicket trainTicket;

    public TicketWindow(TrainTicket trainTicket, String name) {
        this.trainTicket = trainTicket;
        this.setName(name);
    }

    @Override
    public void run() {
        while (trainTicket.getAvailableSeats() > 0) {
            trainTicket.sellTicket();
        }
    }
}

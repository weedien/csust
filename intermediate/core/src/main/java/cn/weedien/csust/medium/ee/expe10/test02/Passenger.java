package cn.weedien.csust.medium.ee.expe10.test02;

class Passenger implements Runnable {
    private String name;
    private RideShare rideShare;

    public Passenger(String name, RideShare rideShare) {
        this.name = name;
        this.rideShare = rideShare;
    }

    @Override
    public void run() {
        try {
            rideShare.joinRide(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
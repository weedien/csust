package cn.weedien.ee.expe08.test03;

class Seller {
    private int stock;

    public Seller(int stock) {
        this.stock = stock;
    }

    public synchronized boolean makePurchase() {
        if (stock > 0) {
            stock--;
            return true;
        }
        return false;
    }
}

package cn.weedien.csust.advanced.po;

public class Order {
    private String oid;

    private String orderName;

    private String uid;

    private String orderTime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", orderName='" + orderName + '\'' +
                ", uid='" + uid + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}

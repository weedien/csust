package cn.weedien.csust.po;

public class Order {
    private String oid;

    private  String orderName;

    private String uid;

    private String orderTime;

    public String getOid() {
        return oid;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getUid() {
        return uid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

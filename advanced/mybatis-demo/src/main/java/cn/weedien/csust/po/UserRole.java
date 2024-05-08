package cn.weedien.csust.po;

public class UserRole {

    private String urid;

    private String uid;

    private String rid;

    public String getUrid() {
        return urid;
    }

    public void setUrid(String urid) {
        this.urid = urid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "urid='" + urid + '\'' +
                ", uid='" + uid + '\'' +
                ", rid='" + rid + '\'' +
                '}';
    }
}

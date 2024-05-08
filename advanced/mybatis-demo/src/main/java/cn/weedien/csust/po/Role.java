package cn.weedien.csust.po;

public class Role {
    private String rid;

    private String roleName;

    public String getRid() {
        return rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid='" + rid + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

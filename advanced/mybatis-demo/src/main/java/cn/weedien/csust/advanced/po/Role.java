package cn.weedien.csust.advanced.po;

public class Role {
    private String rid;

    private String roleName;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
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

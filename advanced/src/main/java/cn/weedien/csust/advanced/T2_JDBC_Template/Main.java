package cn.weedien.csust.advanced.T2_JDBC_Template;

public class Main {
    public static void main(String[] args) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = JDBCTemplate.queryForObject(sql, User.class, 1);
        System.out.println(user);
    }
}
package cn.weedien.csust.advanced.T3_connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicDataSource extends DataSource {
    @Override
    public Connection getConnection() throws SQLException {
        // 这里直接返回一个新的Connection对象,没有被代理
        return DriverManager.getConnection("jdbc:mysql://remote:3306/test", "weedien", "031209");
    }
}
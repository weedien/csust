package cn.weedien.csust.advanced.T3_connection_pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCPDataSource extends DataSource {
    private final List<Connection> connectionPool = new ArrayList<>();

    @Override
    public Connection getConnection() throws SQLException {
        // 从连接池中获取一个Connection,如果池中没有,则创建一个新的Connection
        Connection conn = connectionPool.isEmpty() ? createConnection() : connectionPool.remove(0);
        // 为Connection创建一个代理对象
        return (Connection) Proxy.newProxyInstance(
                Connection.class.getClassLoader(),
                new Class[]{Connection.class},
                new ConnectionHandler(conn, connectionPool));
    }

    private Connection createConnection() throws SQLException {
        // 创建一个新的Connection对象
        return DriverManager.getConnection("jdbc:mysql://remote:3306/test", "weedien", "031209");
    }
}
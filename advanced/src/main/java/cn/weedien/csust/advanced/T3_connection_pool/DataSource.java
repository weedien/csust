package cn.weedien.csust.advanced.T3_connection_pool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataSource {
    public abstract Connection getConnection() throws SQLException;
}
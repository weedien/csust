package cn.weedien.csust.advanced.T3_connection_pool;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource basicDataSource = new BasicDataSource();
        JDBCTemplate basicTemplate = new JDBCTemplate(basicDataSource);
        basicTemplate.executeUpdateWithParams("INSERT INTO student (name, class) VALUES ( ?, ?)", "张三", "2101");

        DataSource dbcpDataSource = new DBCPDataSource();
        JDBCTemplate dbcpTemplate = new JDBCTemplate(dbcpDataSource);
        dbcpTemplate.executeUpdateWithParams("INSERT INTO student (name, class) VALUES ( ?, ?)", "李四", "2102");
    }
}

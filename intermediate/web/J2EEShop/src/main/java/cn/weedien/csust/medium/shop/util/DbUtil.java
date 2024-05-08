package cn.weedien.csust.medium.shop.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class DbUtil {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    /**
     * 返回一个连接池对象
     */
    @Getter
    private static DataSource dataSource;

    // 静态代码快块，用于初始化连接池对象
    static {
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("初始化连接池失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     */
    public static Connection getConnection() throws SQLException {
        Connection con = threadLocal.get();
        if (con == null) {
            con = dataSource.getConnection();
            threadLocal.set(con);
        }
        return con;
    }

    /**
     * 获取连接对象，开启事务
     */
    public static Connection startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.setAutoCommit(false);
        }
        return con;
    }

    /**
     * 事务回滚
     */
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }

    /**
     * 事务提交，资源关闭
     */
    public static void commitAndRelease() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.commit();
            con.close();
            threadLocal.remove();
        }
    }
}

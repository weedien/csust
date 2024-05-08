### 1. 任务描述

（1）掌握JDBC访问数据的接口及过程

（2）编写Util类，提供getConnection和closeConnection的操作

（3）编写JDBCTemplate类，提供querForObject方法，传入sql语句及参数、返回类型

（4）利用数据库查询的一般步骤实现数据库的访问，并根据返回类型利用反射构建对象，并反射调用set函数，实现数据库表格中实体自动封装为java实体对象并返回

（5）编写测试类测试程序

（6）将问题求解思路、核心代码、程序运行截图形成一个word文档提交

### 2. 运行结果截图

- 数据库表users中的数据
  ![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/20240306163235.png)

- 执行SQL：SELECT * FROM users WHERE id = 1;
  ![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/20240306162941.png)

### 3. 代码展示

- JDBC工具类

```java
public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

- JDBCTemplate

```java
public class JDBCTemplate {
    public static <T> T queryForObject(String sql, Class<T> clazz, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (rs.next()) {
                T obj = clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, value);
                }
                return obj;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    private static void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

- User实体类

```java
public class User {
    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

- 测试类

```java
public class Main {
    public static void main(String[] args) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = JDBCTemplate.queryForObject(sql, User.class, 1);
        System.out.println(user);
    }
}
```
## 1. 任务要求

掌握MyBatis的查询技术，完成如下任务：

（1）设计用户表（UID，NAME，PASSWORD）和订单表（OID，ORDERNAME，UID，ORDERTIME）；设计角色表（RID，ROLENAME）和用户角色表（URID，UID，RID）

（2）利用配置文件和注解两种方式完成用户订单的一对一、一对多的查询；

（3）利用配置文件和注解两种方式完成用户角色的多对多查询

（4）将数据库设计、配置文件和源代码、程序运行截图形成一个word文件提交

## 2. 配置与代码

### 2.1 Mapper配置

- OrderMapper

```java

@Mapper
public interface OrderMapper {

    @Select(
            "SELECT * FROM `orders` WHERE uid = #{uid}"
    )
    List<Order> getByUserId(String uid);

    @Select(
            "SELECT * FROM `orders` WHERE oid = #{oid}"
    )
    Order getById(String oid);
}
```

```xml

<mapper namespace="com.example.dao.OrderMapper">
    <resultMap id="orderMap" type="com.example.po.Order">
        <id property="oid" column="oid"/>
        <result property="uid" column="uid"/>
        <result property="orderName" column="order_name"/>
        <result property="orderTime" column="order_time"/>
    </resultMap>

    <sql id="orderColumns">
        oid, uid, order_name, order_time
    </sql>

    <select id="getByUserId" parameterType="string" resultMap="orderMap">
        SELECT
        <include refid="orderColumns"/>
        FROM orders
        WHERE uid = #{uid}
    </select>

    <select id="getById" parameterType="string" resultMap="orderMap">
        SELECT
        <include refid="orderColumns"/>
        FROM orders
        WHERE oid = #{oid}
    </select>

</mapper>
```

- UserMapper

```java

@Mapper
public interface UserMapper {

    @Select(
            "SELECT * FROM users WHERE uid = #{uid}"
    )
    User getUserById(String uid);
}
```

```xml

<mapper namespace="com.example.dao.UserMapper">

    <select id="getUserById" parameterType="string" resultType="com.example.po.User">
        SELECT uid, `name`, `password`
        FROM users
        WHERE uid = #{uid}
    </select>

</mapper>
```

- RoleMapper

```java

@Mapper
public interface RoleMapper {

    @Select(
            "SELECT role_name FROM roles WHERE rid = #{rid}"
    )
    String getRoleNameById(String rid);
}
```

```xml

<mapper namespace="com.example.dao.RoleMapper">

    <select id="getRoleNameById" parameterType="string" resultType="string">
        SELECT role_name
        FROM roles
        WHERE rid = #{rid}
    </select>
</mapper>
```

- UserRoleMapper

```java

@Mapper
public interface UserRoleMapper {

    @Select(
            "SELECT rid FROM user_roles WHERE uid = #{uid}"
    )
    List<String> getRoleIdsByUserId(String uid);
}
```

```xml

<mapper namespace="com.example.dao.UserRoleMapper">
    <resultMap id="userRoleMap" type="com.example.po.UserRole">
        <id property="urid" column="urid"/>
        <result property="uid" column="uid"/>
        <result property="rid" column="rid"/>
    </resultMap>

    <select id="getRoleIdsByUserId" resultType="string">
        SELECT rid
        FROM user_roles
        WHERE uid = #{uid}
    </select>
</mapper>
```

### 2.2 测试代码

```java

@SpringBootTest
class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户指定订单 -- 单行数据
     */
    @Test
    void selectOrderWithOrderId() {
        Order order = orderMapper.getById("1");
        log.info("order: {}", order);
    }

    /**
     * 查询用户的所有订单 -- 多行数据
     */
    @Test
    void selectOrdersWithUserId() {
        List<Order> orders = orderMapper.getByUserId("1");
        orders.forEach(order -> log.info(order.toString()));
    }

    /**
     * 查询用户的所有角色 -- 多行数据
     */
    @Test
    void selectRolesWithUserId() {
        String uid = "1";
        List<String> userRoles = new ArrayList<>();

        List<String> rids = userRoleMapper.getRoleIdsByUserId(uid);
        for (String rid : rids) {
            String roleName = roleMapper.getRoleNameById(rid);
            userRoles.add(roleName);
        }

        User user = userMapper.getUserById(uid);

        log.info("user: {}", user.getName());
        log.info("roles: {}", userRoles);
    }

}
```

## 3. 运行结果

- 根据订单号查询用户订单 -- 单行数据

![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/2024%2F04%2F24%2F20240424170925.png)

- 根据用户Id查询用户订单 -- 多行数据

![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/2024%2F04%2F24%2F20240424170942.png)

- 根据用户Id查询用户角色 -- 多行数据

![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/2024%2F04%2F24%2F20240424170953.png)

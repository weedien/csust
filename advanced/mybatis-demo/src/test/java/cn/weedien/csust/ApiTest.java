package cn.weedien.csust;

import cn.weedien.csust.advanced.dao.OrderMapper;
import cn.weedien.csust.advanced.dao.RoleMapper;
import cn.weedien.csust.advanced.dao.UserMapper;
import cn.weedien.csust.advanced.dao.UserRoleMapper;
import cn.weedien.csust.advanced.po.Order;
import cn.weedien.csust.advanced.po.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    void selectOrderWithOid() {
        Order order = orderMapper.getById("1");
        log.info("order: {}", order);
    }

    /**
     * 查询用户的所有订单 -- 多行数据
     */
    @Test
    void listOrderWithUid() {
        List<Order> orders = orderMapper.getByUserId("1");
        orders.forEach(order -> log.info(order.toString()));
    }

    /**
     * 查询用户的所有角色 -- 多行数据
     */
    @Test
    void listRoleWithUid() {
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

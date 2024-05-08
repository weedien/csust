package cn.weedien.csust.medium.shop.dao;

import cn.weedien.csust.medium.shop.domain.Order;
import cn.weedien.csust.medium.shop.domain.OrderItem;
import cn.weedien.csust.medium.shop.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 订单操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface OrderDao {

    Order findByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;

    int findTotalRecord() throws SQLException;

    int findTotalRecordByState(int state) throws SQLException;

    int findTotalRecordByUid(User loginUser) throws SQLException;

    void insert(Connection connection, Order order) throws SQLException;

    void insert(Connection connection, OrderItem orderItem) throws SQLException;

    void updateStatus(Connection connection, Order order) throws SQLException;

    void delete(String oid) throws SQLException;

    List<Order> list(int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Order> listByState(int state, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Order> listByUid(User loginUser, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;
}

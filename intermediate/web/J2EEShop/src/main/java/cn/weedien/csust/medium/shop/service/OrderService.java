package cn.weedien.csust.medium.shop.service;

import cn.weedien.csust.medium.shop.domain.Order;
import cn.weedien.csust.medium.shop.domain.PageBean;
import cn.weedien.csust.medium.shop.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * 订单业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface OrderService {

    int transfer(String oid, String from, String to, double money) throws SQLException;

    Order findByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void insert(Order order) throws SQLException;

    void delete(String oid) throws SQLException;

    PageBean<Order> list(int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException;

    PageBean<Order> listByUid(User loginUser, int pageNumber, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;

    PageBean<Order> listByState(int state, int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException;
}

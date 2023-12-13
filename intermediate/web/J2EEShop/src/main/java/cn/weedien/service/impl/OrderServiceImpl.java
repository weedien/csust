package cn.weedien.service.impl;

import cn.weedien.dao.OrderDao;
import cn.weedien.dao.impl.OrderDaoImpl;
import cn.weedien.domain.Order;
import cn.weedien.domain.OrderItem;
import cn.weedien.domain.PageBean;
import cn.weedien.domain.User;
import cn.weedien.service.OrderService;
import cn.weedien.util.DbUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 订单业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();

    /**
     * 分页查询订单
     */
    @Override
    public PageBean<Order> listByUid(User loginUser, int pageNumber, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        // 先查询数据库获得总的订单数
        int totalRecord = orderDao.findTotalRecordByUid(loginUser);
        // 通过pageBean的构造方法获取startIndex和pageSize
        PageBean<Order> pageBean = new PageBean<>(pageNumber, pageSize, totalRecord);
        // 分页查询
        List<Order> data = orderDao.listByUid(loginUser, pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public void insert(Order order) throws SQLException {
        // 如果直接调用getConnection方法，会报autoCommit异常，换用开启事务方法，禁止autoCommit
        Connection connection = DbUtil.startTransaction();
        orderDao.insert(connection, order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderDao.insert(connection, orderItem);
        }
        DbUtil.commitAndRelease();
    }

    @Override
    public int transfer(String oid, String from, String to, double money) throws SQLException {
        Connection connection = DbUtil.getConnection();
        Order order = new Order();
        order.setOid(oid);
        order.setState(2); // 已付款未发货
        orderDao.updateStatus(connection, order);
        return 0;
    }

    /**
     * 根据oid查找订单
     */
    @Override
    public Order findByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        return orderDao.findByOid(oid);
    }

    /**
     * 商城后台显示所有订单
     */
    @Override
    public PageBean<Order> list(int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException {

        int totalRecord = orderDao.findTotalRecord();

        PageBean<Order> pageBean = new PageBean<>(pageNumber, pageSize, totalRecord);

        List<Order> list = orderDao.list(pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(list);

        return pageBean;
    }

    /**
     * 商城后台按照订单状态显示订单
     */
    @Override
    public PageBean<Order> listByState(int state, int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException {

        int totalRecord = orderDao.findTotalRecordByState(state);

        PageBean<Order> pageBean = new PageBean<>(pageNumber, pageSize, totalRecord);

        List<Order> list = orderDao.listByState(state, pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(list);

        return pageBean;
    }

    @Override
    public void delete(String oid) throws SQLException {
        orderDao.delete(oid);
    }


}

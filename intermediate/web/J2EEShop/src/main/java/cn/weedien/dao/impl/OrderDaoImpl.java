package cn.weedien.dao.impl;

import cn.weedien.dao.OrderDao;
import cn.weedien.dao.UserDao;
import cn.weedien.domain.Order;
import cn.weedien.domain.OrderItem;
import cn.weedien.domain.Product;
import cn.weedien.domain.User;
import cn.weedien.util.DbUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 订单操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class OrderDaoImpl implements OrderDao {

    UserDao userDao = new UserDaoImpl();

    /**
     * 根据用户id查找该用户的订单总数
     */
    @Override
    public int findTotalRecordByUid(User loginUser) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from orders where uid = ?";
        Long count = (Long) queryRunner.query(sql, new ScalarHandler(), loginUser.getUid());
        return count.intValue();
    }

    /**
     * 根据用户id查找该用户的所有订单详细信息
     */
    @Override
    public List<Order> listByUid(User loginUser, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        // 查询数据库获得该用户所有订单order
        String sql = "select * from orders where uid = ? order by ordertime desc limit ?,?";
        List<Order> list = queryRunner.query(sql, new BeanListHandler<>(Order.class), loginUser.getUid(), startIndex, pageSize);
        // 使用循环查询orderitem和product，将两张表的属性封装到一个map中
        for (Order order : list) {
            sql = "Select * from orderitem o ,product p where oid = ? and o.pid = p.pid";
            // 因为一个oid能查出多个orderitem(即一个订单中有多个订单项)，所以查询结果用List集合封装
            List<Map<String, Object>> oList = queryRunner.query(sql, new MapListHandler(), order.getOid());
            for (Map<String, Object> map : oList) {
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem, map);
                Product product = new Product();
                BeanUtils.populate(product, map);

                orderItem.setProduct(product);
                orderItem.setOid(order.getOid());

                order.getOrderItems().add(orderItem);
            }
            order.setUser(loginUser);
        }
        return list;
    }

    /**
     * 插入订单
     */
    @Override
    public void insert(Connection connection, Order order) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
        Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(),
                order.getState(), order.getAddress(), order.getName(),
                order.getTelephone(), order.getUser().getUid()};
        queryRunner.update(connection, sql, params);
    }

    /**
     * 插入订单项
     */
    @Override
    public void insert(Connection connection, OrderItem orderItem) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orderitem values (?,?,?,?,?)";
        Object[] params = {orderItem.getItemid(), orderItem.getCount(),
                orderItem.getSubtotal(), orderItem.getProduct().getPid(),
                orderItem.getOid()};
        queryRunner.update(connection, sql, params);
    }

    @Override
    public void updateStatus(Connection connection, Order order) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update orders set state = ? where oid = ?";
        Object[] params = {order.getState(), order.getOid()};
        queryRunner.update(connection, sql, params);
    }

    /**
     * 根据oid查找订单
     */
    @Override
    public Order findByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from orders where oid = ? ";
        Order order = queryRunner.query(sql, new BeanHandler<>(Order.class), oid);

        sql = "Select * from orderitem o ,product p where oid = ? and o.pid = p.pid";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);
        List<OrderItem> orderItemList = new LinkedList<>();
        for (Map<String, Object> map : list) {
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem, map);

            Product product = new Product();
            BeanUtils.populate(product, map);

            orderItem.setProduct(product);
            orderItem.setOid(order.getOid());

            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        return order;
    }

    @Override
    public int findTotalRecord() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from orders";
        Long count = queryRunner.query(sql, new ScalarHandler<>());
        return count.intValue();
    }

    @Override
    public int findTotalRecordByState(int state) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from orders where state = ?";
        Long count = queryRunner.query(sql, new ScalarHandler<>(), state);
        return count.intValue();
    }

    @Override
    public void delete(String oid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "delete from orderitem where oid = ?";
        queryRunner.update(sql, oid);
        sql = "delete from orders where oid = ?";
        queryRunner.update(sql, oid);
    }

    /**
     * 查询所有的用户订单，多表查询，用于后台管理界面
     */
    @Override
    public List<Order> list(int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from orders order by ordertime desc limit ?,?";
        List<Order> orderList = queryRunner.query(sql, new BeanListHandler<>(Order.class), startIndex, pageSize);

        for (Order order : orderList) {
            // 查询订单项
            order = this.findByOid(order.getOid());
        }

        return orderList;
    }

    @Override
    public List<Order> listByState(int state, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from orders where state = ? order by ordertime desc limit ?,?";
        List<Order> orderList = queryRunner.query(sql, new BeanListHandler<>(Order.class), state, startIndex, pageSize);

        for (Order order : orderList) {
            order = this.findByOid(order.getOid());
            User user = userDao.findByUid(order.getUser().getUid());
        }

        return orderList;
    }
}

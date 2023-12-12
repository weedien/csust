package cn.weedien.dao.impl;

import cn.weedien.dao.UserDao;
import cn.weedien.domain.User;
import cn.weedien.util.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 用户数据操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User findByName(String username) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from user where username = ? ";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username);
    }

    @Override
    public int insert(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(),
                user.getSex(), user.getState()
        };
        return queryRunner.update(sql, params);
    }

    @Override
    public User findByCode(String code) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from user where code = ? ";
        return queryRunner.query(sql, new BeanHandler<User>(User.class), code);
    }

    @Override
    public void update(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "update user set username=?,password=?,name=?,email=?,telephone=?," +
                "birthday=?,sex=?,state=? where uid=?";
        Object[] params = new Object[]{user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(),
                user.getSex(), user.getState(), user.getUid()};
        queryRunner.update(sql, params);
    }

    @Override
    public User findByUid(String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from user where uid = ? ";
        return queryRunner.query(sql, new BeanHandler<>(User.class), uid);
    }
}

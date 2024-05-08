package cn.weedien.csust.medium.shop.dao;

import cn.weedien.csust.medium.shop.domain.User;

import java.sql.SQLException;

/**
 * 用户数据操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface UserDao {

    User findByName(String username) throws SQLException;

    User findByCode(String code) throws SQLException;

    User findByUid(String username) throws SQLException;

    int insert(User user) throws SQLException;

    void update(User existUser) throws SQLException;
}

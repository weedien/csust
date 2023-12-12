package cn.weedien.service;

import cn.weedien.domain.User;

import java.sql.SQLException;

/**
 * 用户业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface UserService {

    User findByName(String username) throws SQLException;

    int register(User user) throws SQLException;

    User login(String username, String password) throws SQLException;
}

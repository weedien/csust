package cn.weedien.service.impl;


import cn.weedien.dao.UserDao;
import cn.weedien.dao.impl.UserDaoImpl;
import cn.weedien.domain.User;
import cn.weedien.service.UserService;
import cn.weedien.util.Md5Util;

import java.sql.SQLException;

/**
 * 用户业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User findByName(String username) throws SQLException {
        return userDao.findByName(username);
    }

    @Override
    public int register(User user) throws SQLException {

        return userDao.insert(user);
    }

    @Override
    public User login(String username, String password) throws SQLException {

        User user = findByName(username);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(Md5Util.md5(password))) {
            return user;
        } else {
            return null;
        }
    }

}

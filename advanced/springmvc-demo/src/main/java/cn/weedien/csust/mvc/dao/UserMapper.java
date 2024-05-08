package cn.weedien.csust.mvc.dao;

import cn.weedien.csust.mvc.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(
            "insert into user(username, password, email, phone) " +
                    "values(#{username}, #{password}, #{email}, #{phone})"
    )
    void insert(User user);

    @Select(
            "select * from user where username = #{username}"
    )
    User selectOne(String username);

    @Select(
            "select * from user"
    )
    List<User> list();
}

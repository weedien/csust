package cn.weedien.csust.dao;

import cn.weedien.csust.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select(
            "SELECT * FROM users WHERE uid = #{uid}"
    )
    User getUserById(String uid);
}

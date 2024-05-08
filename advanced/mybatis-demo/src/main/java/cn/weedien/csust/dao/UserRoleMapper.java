package cn.weedien.csust.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    @Select(
            "SELECT rid FROM user_roles WHERE uid = #{uid}"
    )
    List<String> getRoleIdsByUserId(String uid);
}

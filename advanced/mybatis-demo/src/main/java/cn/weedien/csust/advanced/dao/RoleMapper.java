package cn.weedien.csust.advanced.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {

    @Select(
            "SELECT role_name FROM roles WHERE rid = #{rid}"
    )
    String getRoleNameById(String rid);
}

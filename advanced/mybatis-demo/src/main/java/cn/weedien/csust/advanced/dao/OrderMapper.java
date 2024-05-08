package cn.weedien.csust.advanced.dao;

import cn.weedien.csust.advanced.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select(
            "SELECT * FROM `orders` WHERE uid = #{uid}"
    )
    List<Order> getByUserId(String uid);

    @Select(
            "SELECT * FROM `orders` WHERE oid = #{oid}"
    )
    Order getById(String oid);
}

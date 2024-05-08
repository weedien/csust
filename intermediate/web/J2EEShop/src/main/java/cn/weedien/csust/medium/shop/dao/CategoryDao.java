package cn.weedien.csust.medium.shop.dao;

import cn.weedien.csust.medium.shop.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * 类别操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface CategoryDao {

    int insert(String cid, String cname) throws SQLException;

    int update(String cid, String cname) throws SQLException;

    int delete(String cid) throws SQLException;

    List<Category> list() throws SQLException;
}

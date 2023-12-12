package cn.weedien.service;

import cn.weedien.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * 购物车相关业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface CategoryService {

    int insert(String cid, String cname) throws SQLException;

    int update(String cid, String cname) throws SQLException;

    int remove(String cid) throws SQLException;

    List<Category> list() throws SQLException;
}

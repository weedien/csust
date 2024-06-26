package cn.weedien.csust.medium.shop.service.impl;

import cn.weedien.csust.medium.shop.dao.CategoryDao;
import cn.weedien.csust.medium.shop.dao.impl.CategoryDaoImpl;
import cn.weedien.csust.medium.shop.domain.Category;
import cn.weedien.csust.medium.shop.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * 购物车相关业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> list() throws SQLException {
        return categoryDao.list();
    }

    @Override
    public int remove(String cid) throws SQLException {
        return categoryDao.delete(cid);
    }

    @Override
    public int insert(String cid, String cname) throws SQLException {
        return categoryDao.insert(cid, cname);
    }

    @Override
    public int update(String cid, String cname) throws SQLException {
        return categoryDao.update(cid, cname);
    }
}

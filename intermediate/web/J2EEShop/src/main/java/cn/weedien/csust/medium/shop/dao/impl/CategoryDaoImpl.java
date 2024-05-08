package cn.weedien.csust.medium.shop.dao.impl;

import cn.weedien.csust.medium.shop.dao.CategoryDao;
import cn.weedien.csust.medium.shop.domain.Category;
import cn.weedien.csust.medium.shop.util.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * 类别操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> list() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from category";
        return queryRunner.query(sql, new BeanListHandler<>(Category.class));

    }

    @Override
    public int delete(String cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "update product set cid = null where cid = ?";
        queryRunner.update(sql, cid);

        sql = "delete from category where cid = ?";
        return queryRunner.update(sql, cid);

    }

    @Override
    public int insert(String cid, String cname) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "insert into category values(?,?)";
        Object[] params = {cid, cname};
        return queryRunner.update(sql, params);

    }

    @Override
    public int update(String cid, String cname) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "update category set cname = ? where cid = ?";
        return queryRunner.update(sql, cname, cid);
    }
}

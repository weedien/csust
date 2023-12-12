package cn.weedien.dao.impl;

import cn.weedien.dao.ProductDao;
import cn.weedien.domain.Product;
import cn.weedien.util.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品数据操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> listByword(String word) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where pname like ? limit 0,8";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), "%" + word + "%");

    }

    @Override
    public List<Product> listByHot() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where is_hot = ? and pflag = ? order by pdate desc limit ?";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), 1, 0, 12);
    }

    @Override
    public List<Product> listByNew() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where is_hot = ? and pflag = ? order by pdate desc limit ?";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), 0, 1, 12);
    }


    @Override
    public Product findByPid(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where pid = ?";
        return queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public int findTotalRecordByCid(String cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(), cid);
        return count.intValue();
    }

    @Override
    public List<Product> listByCid(String cid, int startIndex, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where cid = ? order by pdate desc limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public int findTotalRecord() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from product";
        Long count = queryRunner.query(sql, new ScalarHandler<Long>());
        return count.intValue();
    }

    @Override
    public List<Product> list(int startIndex, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where pflag = ? order by pdate desc limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), 0, startIndex, pageSize);
    }

    @Override
    public int delete(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "update orderitem set pid = null where pid = ?";
        queryRunner.update(sql, pid);

        sql = "delete from product where pid = ?";
        return queryRunner.update(sql, pid);
    }

    @Override
    public int insert(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(),
                product.getShop_price(), product.getPimage(), product.getPdate(),
                product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int findTotalRecordByWord(String word) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select count(*) from product where pname like ?";
        Long count = queryRunner.query(sql, new ScalarHandler<Long>(), "%" + word + "%");
        return count.intValue();
    }

    @Override
    public List<Product> listByWord(String word, int startIndex, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DbUtil.getDataSource());
        String sql = "select * from product where pname like ? order by pdate desc limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), "%" + word + "%", startIndex, pageSize);
    }


}


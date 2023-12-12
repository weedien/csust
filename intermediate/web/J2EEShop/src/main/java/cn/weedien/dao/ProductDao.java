package cn.weedien.dao;

import cn.weedien.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品数据操作实现类
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface ProductDao {

    Product findByPid(String pid) throws SQLException;

    int findTotalRecord() throws SQLException;

    int findTotalRecordByWord(String word) throws SQLException;

    int findTotalRecordByCid(String cid) throws SQLException;

    int insert(Product product) throws SQLException;

    int delete(String pid) throws SQLException;

    List<Product> list(int startIndex, int pageSize) throws SQLException;

    List<Product> listByHot() throws SQLException;

    List<Product> listByNew() throws SQLException;

    List<Product> listByword(String word) throws SQLException;

    List<Product> listByWord(String word, int startIndex, int pageSize) throws SQLException;

    List<Product> listByCid(String cid, int startIndex, int pageSize) throws SQLException;


}

package cn.weedien.service;

import cn.weedien.domain.PageBean;
import cn.weedien.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public interface ProductService {

    int insert(Product product) throws SQLException;

    int delete(String pid) throws SQLException;

    Product findById(String pid) throws SQLException;

    List<Product> listByword(String word) throws SQLException;

    List<Product> listByHot() throws SQLException;

    List<Product> listByNew() throws SQLException;

    PageBean<Product> list(int pageNumber, int pageSize) throws SQLException;

    PageBean<Product> listByCid(String cid, int pageNumber, int pageSize) throws SQLException;

    PageBean<Product> listByWord(String word, int pageNumber, int pageSize) throws SQLException;
}

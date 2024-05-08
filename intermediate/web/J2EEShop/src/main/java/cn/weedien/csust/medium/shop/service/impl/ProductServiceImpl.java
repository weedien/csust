package cn.weedien.csust.medium.shop.service.impl;

import cn.weedien.csust.medium.shop.dao.ProductDao;
import cn.weedien.csust.medium.shop.dao.impl.ProductDaoImpl;
import cn.weedien.csust.medium.shop.domain.PageBean;
import cn.weedien.csust.medium.shop.domain.Product;
import cn.weedien.csust.medium.shop.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品业务处理
 *
 * @author weedien
 * @date 2023/12/10
 */
public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> listByword(String word) throws SQLException {

        return productDao.listByword(word);
    }

    @Override
    public List<Product> listByHot() throws SQLException {
        return productDao.listByHot();
    }

    @Override
    public List<Product> listByNew() throws SQLException {
        return productDao.listByNew();
    }

    @Override
    public Product findById(String pid) throws SQLException {
        return productDao.findByPid(pid);
    }

    /**
     * 分页查询某一分类的商品
     */
    @Override
    public PageBean<Product> listByCid(String cid, int pageNumber, int pageSize) throws SQLException {
        // 1.先差查询此类别的商品的总数
        int totalRecord = productDao.findTotalRecordByCid(cid);
        // 2.分页查询
        PageBean<Product> pageBean = new PageBean<Product>(pageNumber, pageSize, totalRecord);

        List<Product> data = productDao.listByCid(cid, pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;

    }

    /**
     * 根据关键字查询商品
     */
    @Override
    public PageBean<Product> listByWord(String word, int pageNumber, int pageSize) throws SQLException {
        // 1.先差查询此类别的商品的总数
        int totalRecord = productDao.findTotalRecordByWord(word);
        // 2.分页查询
        PageBean<Product> pageBean = new PageBean<Product>(pageNumber, pageSize, totalRecord);

        List<Product> data = productDao.listByWord(word, pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;
    }

    @Override
    public PageBean<Product> list(int pageNumber, int pageSize) throws SQLException {

        int totalRecord = productDao.findTotalRecord();

        PageBean<Product> pageBean = new PageBean<>(pageNumber, pageSize, totalRecord);

        List<Product> data = productDao.list(pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;
    }

    @Override
    public int delete(String pid) throws SQLException {
        return productDao.delete(pid);
    }

    @Override
    public int insert(Product product) throws SQLException {
        return productDao.insert(product);
    }
}

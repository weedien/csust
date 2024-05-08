package cn.weedien.csust.medium.shop.servlet;

import cn.weedien.csust.medium.shop.domain.Category;
import cn.weedien.csust.medium.shop.domain.PageBean;
import cn.weedien.csust.medium.shop.domain.Product;
import cn.weedien.csust.medium.shop.service.CategoryService;
import cn.weedien.csust.medium.shop.service.ProductService;
import cn.weedien.csust.medium.shop.service.impl.CategoryServiceImpl;
import cn.weedien.csust.medium.shop.service.impl.ProductServiceImpl;
import cn.weedien.csust.medium.shop.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "AdminProductServlet", urlPatterns = "/adminProductServlet")
public class AdminProductServlet extends BaseServlet {

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询所有商品(使用了分页)
     */
    public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置当前页的页码(默认是1，即第一页)
        int pageNumber = 1;
        String page_number = request.getParameter("pageNumber");
        if (page_number != null) {
            pageNumber = Integer.parseInt(page_number);
        }

        int pageSize = 8;
        PageBean<Product> pageBean = null;
        try {
            pageBean = productService.list(pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("pageBean", pageBean);
        response.sendRedirect(request.getContextPath() + "/admin/product/list.jsp");

    }

    /**
     * 删除商品
     */
    public String productDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String pid = request.getParameter("pid");
        int i = productService.delete(pid);

        return "/adminProductServlet?method=productList";
    }

    /**
     * 跳转到商品添加页面
     */
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        List<Category> list = categoryService.list();
        request.getSession().setAttribute("categoryList", list);

        response.sendRedirect(request.getContextPath() + "/admin/product/add.jsp");

    }

    /**
     * 添加商品
     */
    public String productAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, IllegalAccessException, InvocationTargetException, FileUploadException {

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(3 * 1024 * 1024);

        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        fileUpload.setHeaderEncoding("UTF-8");

        List<FileItem> list = fileUpload.parseRequest(request);

        Map<String, String> map = new HashMap<>();
        String fileName = null;
        for (FileItem fileItem : list) {
            if (fileItem.isFormField()) {
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + " " + value);
                map.put(name, value);
            } else {
                fileName = fileItem.getName();
                System.out.println("文件名" + fileName);

                InputStream in = fileItem.getInputStream();
                String path = this.getServletContext().getRealPath("/products/1");
                OutputStream out = Files.newOutputStream(Paths.get(path + "/" + fileName));
                IOUtils.copy(in, out);

                int length;
                byte[] b = new byte[1024];
                while ((length = in.read(b)) != -1) {
                    out.write(b, 0, length);
                }
                in.close();
                out.close();
            }
        }
        Product product = new Product();
        BeanUtils.populate(product, map);
        product.setPid(UUIDUtil.getUUID());
        product.setPdate(new Date());
        product.setPflag(0);
        product.setPimage("products/1/" + fileName);
        product.setCid(map.get("cid"));

        productService.insert(product);

        return "/adminProductServlet?method=productList";
    }
}

package cn.weedien.csust.medium.shop.servlet;

import cn.weedien.csust.medium.shop.domain.Category;
import cn.weedien.csust.medium.shop.service.CategoryService;
import cn.weedien.csust.medium.shop.service.impl.CategoryServiceImpl;
import cn.weedien.csust.medium.shop.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品分类
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "AdminCategoryServlet", urlPatterns = "/adminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryServiceImpl();

    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Category> allCategory = new LinkedList<>();
        try {
            allCategory = categoryService.list();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("allCategory", allCategory);
        response.sendRedirect(request.getContextPath() + "/admin/category/list.jsp");
    }

    public String categoryRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String cid = request.getParameter("cid");
        int i = categoryService.remove(cid);

        return "/adminCategoryServlet?method=categoryList";
    }

    public String categoryAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String cname = request.getParameter("cname");
        String cid = UUIDUtil.getUUID();
        int i = categoryService.insert(cid, cname);

        return "/adminCategoryServlet?method=categoryList";
    }

    /**
     * 跳转到修改页面
     */
    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String cname = request.getParameter("cname");
        String cid = request.getParameter("cid");
        Category category = new Category();
        category.setCname(cname);
        category.setCid(cid);
        request.getSession().setAttribute("categoryItem", category);

        response.sendRedirect(request.getContextPath() + "/admin/category/edit.jsp");
    }

    /**
     * 修改
     */
    public String categoryUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String cname = request.getParameter("cname");
        String cid = request.getParameter("cid");
        categoryService.update(cid, cname);

        return "/adminCategoryServlet?method=categoryList";
    }


}

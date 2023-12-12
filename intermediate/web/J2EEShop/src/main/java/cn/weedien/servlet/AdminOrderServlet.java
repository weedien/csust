package cn.weedien.servlet;

import cn.weedien.domain.Order;
import cn.weedien.domain.PageBean;
import cn.weedien.service.OrderService;
import cn.weedien.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * 后台订单管理
 */
@WebServlet(name = "AdminOrderServlet", urlPatterns = "/adminOrderServlet")
public class AdminOrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    /**
     * 修改分类
     */
    public void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException {

        String state_param = request.getParameter("state");

        int pageNumber = 1;
        String page_number = request.getParameter("pageNumber");
        if (page_number != null) {
            pageNumber = Integer.parseInt(page_number);
        }

        int pageSize = 8;
        PageBean<Order> pageBean = null;
        try {
            pageBean = orderService.list(pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("pageBean", pageBean);
        response.sendRedirect(request.getContextPath() + "/admin/order/list.jsp");

    }

    /**
     * 修改分类
     */
    public String findOrderByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException {

        String state_param = request.getParameter("state");
        int state = 0;
        if (state_param != null) {
            state = Integer.parseInt(state_param);
        }

        int pageNumber = 1;
        String page_number = request.getParameter("pageNumber");
        if (page_number != null) {
            pageNumber = Integer.parseInt(page_number);
        }

        int pageSize = 8;
        PageBean<Order> pageBean = null;
        try {
            pageBean = orderService.listByState(state, pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("pageBean", pageBean);

        return "/adminOrderServlet?method=findOrderByState";
    }
}
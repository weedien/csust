package cn.weedien.servlet;

import cn.weedien.domain.*;
import cn.weedien.service.OrderService;
import cn.weedien.service.impl.OrderServiceImpl;
import cn.weedien.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 处理和订单相关的请求
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/orderServlet")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    public void findByUid(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, SQLException, InvocationTargetException {
        // 设置页码
        int pageNumber = 1;
        String number = request.getParameter("pageNumber");
        if (number != null) {
            pageNumber = Integer.parseInt(number);
        }
        // 每页显示数(暂定)
        int pageSize = 3;

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        PageBean<Order> pageBean = orderService.listByUid(loginUser, pageNumber, pageSize);

        request.getSession().setAttribute("orderList", pageBean);

        response.sendRedirect(request.getContextPath() + "/jsp/order_list.jsp");
    }

    /**
     * 根据购物车中的商品创建订单
     */
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Order order = new Order();
        order.setOid(UUIDUtil.getUUID());
        order.setState(1);
        order.setOrdertime(new Timestamp(System.currentTimeMillis()));
        order.setTotal(cart.getTotal());
        order.setUser(loginUser);
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtil.getUUID());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOid(order.getOid());
            order.getOrderItems().add(orderItem);
        }
        orderService.insert(order);

        cart.clearCart();
        response.sendRedirect(request.getContextPath() + "/orderServlet?method=findByUid");
    }

    /**
     * 根据oid数据库显示订单详情
     */
    public void orderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, InvocationTargetException, IllegalAccessException {
        String oid = request.getParameter("oid");
        // 将oid存入session中，方便支付时使用
        request.getSession().setAttribute("oid", oid);
        Order order = orderService.findByOid(oid);
        request.getSession().setAttribute("order", order);
        response.sendRedirect(request.getContextPath() + "/jsp/order_info.jsp");
    }

    public void orderPay(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String address = request.getParameter("address");
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String pd_FrpId = request.getParameter("pd_FrpId");
        String money = request.getParameter("money");

        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("to", pd_FrpId);
        request.getSession().setAttribute("money", money);

        response.sendRedirect(request.getContextPath() + "/jsp/transfer.jsp");
    }

    public void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        String oid = request.getSession().getAttribute("oid").toString();

        String name = request.getParameter("name");
        String to = request.getParameter("to");
        String money = request.getParameter("money");

        System.out.println(name + "向" + to + "支付了" + money + "元");
        int status = orderService.transfer(oid, name, to, Double.parseDouble(money));

        if (status == 0) {
            request.getSession().setAttribute("msg", "支付成功");
        } else {
            request.getSession().setAttribute("msg", "支付失败");
        }

        response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
    }

    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String oid = request.getParameter("oid");
        orderService.delete(oid);
        response.sendRedirect(request.getContextPath() + "/orderServlet?method=findByUid");
    }


}

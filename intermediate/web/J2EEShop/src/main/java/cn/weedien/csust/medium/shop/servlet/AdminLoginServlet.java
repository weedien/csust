package cn.weedien.csust.medium.shop.servlet;

import cn.weedien.csust.medium.shop.domain.User;
import cn.weedien.csust.medium.shop.service.UserService;
import cn.weedien.csust.medium.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 用户登录
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = "/adminServlet")
public class AdminLoginServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        if (user != null) {
            request.getSession().setAttribute("loginAdmin", user);
            response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
        } else {
            request.getSession().setAttribute("msg", "用户名或密码不匹配或未激活");
            response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
        }
    }
}
